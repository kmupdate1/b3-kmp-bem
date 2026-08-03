package org.b3.bem.server.store

import kotlinx.serialization.json.Json
import org.b3.bem.model.codec.json.JsonCodec
import org.b3.bem.protocol.http.Api
import org.b3.bem.protocol.http.HttpProtocol
import org.b3.bem.server.store.config.StoreConfig
import org.b3.ioe.config.ConfigLoader
import org.b3.ioe.config.Parser
import org.b3.ioe.config.root.IoEConfig
import org.b3.ioe.http.HttpServer
import org.b3.ioe.http.routing.routing
import org.b3.ioe.ktor.KtorHttpServer
import org.b3.ioe.mongo.MongoStore
import org.b3.runtime.lifecycle.Lifecycle

class Server(
    private val args: Array<String>,
) : Lifecycle {
    override suspend fun onCreate() {
        httpServer.create()
    }

    override suspend fun onStart() {
        httpServer.start()
    }

    override suspend fun onStop() {
        httpServer.stop()
    }

    override suspend fun onDestroy() {
        httpServer.destroy()
    }

    private val source = ConfigLoader.load(args = args)
    private val ioeConfig = Parser.parse<IoEConfig>(source = source)
    private val storeConfig = Parser.parse<StoreConfig>(source = source)

    private val codec = JsonCodec(Json { ignoreUnknownKeys = true })
    private val database = MongoStore(url = ioeConfig.mongo.url)
        .database(storeConfig.database)

    private val storeComponent = StoreComponent(codec = codec, database = database)
    private val httpServer: HttpServer = KtorHttpServer(
        host = ioeConfig.ktor.host, port = ioeConfig.ktor.port,
        routes = routing {
            path(Api.BASE_PATH) {
                path(HttpProtocol.VERSION) { include(storeComponent.endpoint.routes) }
            }
        },
    )
}
