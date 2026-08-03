package org.b3.bem.server

import com.mongodb.kotlin.client.coroutine.MongoClient
import kotlinx.serialization.json.Json
import org.b3.bem.model.codec.json.JsonCodec
import org.b3.bem.protocol.http.Api
import org.b3.bem.protocol.http.HttpProtocol
import org.b3.bem.server.application.FactsService
import org.b3.bem.server.http.endpoint.FactsEndpoint
import org.b3.bem.server.storage.mongo.MongoFactRepository
import org.b3.bem.server.storage.mongo.MongoStore
import org.b3.ioe.http.HttpServer
import org.b3.ioe.http.routing.routing
import org.b3.ioe.ktor.KtorHttpServer
import org.b3.runtime.lifecycle.Lifecycle

class BemServer(
    private val host: String = "0.0.0.0",
    private val port: Int = 8080,
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
    private val client = MongoClient.create("mongodb://127.0.0.1:27017")
    private val store = MongoStore(client = client)
    private val service = FactsService(repository = MongoFactRepository(codec = codec, database = store.database(DATABASE)))
    private val factsEndpoint = FactsEndpoint(service = service)
    private val httpServer: HttpServer = KtorHttpServer(
        host,
        port,
        routes = routing {
            path(Api.BASE_PATH) {
                path(HttpProtocol.VERSION) { include(factsEndpoint.routes) }
            }
        },
    )

    private companion object {
        const val DATABASE = "business_event_management"
    }
}
