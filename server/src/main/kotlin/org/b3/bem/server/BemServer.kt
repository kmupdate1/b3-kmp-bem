package org.b3.bem.server

import org.b3.bem.server.application.FactsService
import org.b3.bem.server.http.HttpServer
import org.b3.bem.server.http.ktor.BemKtorHttpServer
import org.b3.bem.server.storage.mongo.MongoFactRepository
import org.b3.runtime.core.lifecycle.Lifecycle

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

    private val httpServer: HttpServer = BemKtorHttpServer(host, port, service = FactsService(repository = MongoFactRepository()))
}
