package org.b3.bem.server

import org.b3.bem.protocol.http.Api
import org.b3.bem.protocol.http.HttpProtocol
import org.b3.bem.server.application.FactsService
import org.b3.bem.server.http.endpoint.FactsEndpoint
import org.b3.bem.server.storage.mongo.MongoFactRepository
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

    private val service = FactsService(repository = MongoFactRepository())
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
}
