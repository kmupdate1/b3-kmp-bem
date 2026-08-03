package org.b3.bem.server.store

import kotlinx.serialization.json.Json
import org.b3.bem.model.codec.json.JsonCodec
import org.b3.bem.protocol.http.Api
import org.b3.bem.protocol.http.HttpProtocol
import org.b3.bem.server.store.application.FactsService
import org.b3.bem.server.store.endpoint.FactsEndpoint
import org.b3.bem.server.store.storage.mongo.MongoFactRepository
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

    private val codec = JsonCodec(Json { ignoreUnknownKeys = true })
    private val store = MongoStore(url = "mongodb://$DATABASE_HOST:$DATABASE_PORT")
    private val service = FactsService(repository = MongoFactRepository(encoder = codec, database = store.database(DATABASE)))
    private val factsEndpoint = FactsEndpoint(decoder = codec, service = service)
    private val httpServer: HttpServer = KtorHttpServer(
        host = HTTP_HOST, port = HTTP_PORT,
        routes = routing {
            path(Api.BASE_PATH) {
                path(HttpProtocol.VERSION) { include(factsEndpoint.routes) }
            }
        },
    )

    private companion object {
        const val LOCALHOST = "127.0.0.1"
        const val HTTP_HOST = LOCALHOST
        const val HTTP_PORT = 8000
        const val DATABASE_HOST = LOCALHOST
        const val DATABASE_PORT = 27017
        const val DATABASE = "business_event_management"
    }
}
