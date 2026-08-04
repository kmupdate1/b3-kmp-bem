package org.b3.bem.server.store

import kotlinx.serialization.json.Json
import org.b3.bem.model.codec.json.JsonCodec
import org.b3.bem.protocol.http.Api
import org.b3.bem.protocol.http.HttpProtocol
import org.b3.bem.server.store.config.StoreConfig
import org.b3.ioe.config.ConfigLoader
import org.b3.ioe.config.Parser
import org.b3.ioe.config.root.IoEConfig
import org.b3.ioe.http.routing.routing
import org.b3.ioe.ktor.server.KtorHttpServer
import org.b3.ioe.mongo.MongoDatabase
import org.b3.ioe.mongo.MongoStore
import org.b3.runtime.lifecycle.Lifecycle

class Server(
    private val args: Array<String>,
) : Lifecycle {
    override suspend fun onCreate() {
        dbStore = MongoStore(url = ioeConfig.mongo.url)
        dbStore.create()
        database = dbStore.database(name = storeConfig.database)

        storeComponent = StoreComponent(codec = codec, database = database)

        httpServer = KtorHttpServer(
            host = ioeConfig.ktor.host, port = ioeConfig.ktor.port,
            routes = routing {
                path(Api.BASE_PATH) {
                    path(HttpProtocol.VERSION) { include(storeComponent.endpoint.routes) }
                }
            },
        )
        httpServer.create()
    }

    override suspend fun onStart() {
        httpServer.start()
    }

    override suspend fun onStop() {
        httpServer.stop()
    }

    override suspend fun onDestroy() {
        dbStore.destroy()
    }

    private val source = ConfigLoader.load(args = args)
    private val ioeConfig = Parser.parse<IoEConfig>(source = source)
    private val storeConfig = Parser.parse<StoreConfig>(source = source)

    private val codec = JsonCodec(Json { ignoreUnknownKeys = true })

    private lateinit var dbStore: MongoStore
    private lateinit var database: MongoDatabase
    private lateinit var storeComponent: StoreComponent
    private lateinit var httpServer: KtorHttpServer
}
