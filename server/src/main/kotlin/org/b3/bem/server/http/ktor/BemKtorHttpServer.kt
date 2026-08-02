package org.b3.bem.server.http.ktor

import io.ktor.http.*
import io.ktor.server.application.*
import io.ktor.server.cio.*
import io.ktor.server.engine.*
import io.ktor.server.plugins.calllogging.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*
import kotlinx.serialization.json.Json
import org.b3.bem.model.codec.json.JsonCodec
import org.b3.bem.model.dto.FactDto
import org.b3.bem.protocol.http.Api
import org.b3.bem.protocol.http.Endpoints
import org.b3.bem.protocol.http.HttpProtocol
import org.b3.bem.server.application.FactsService
import org.b3.bem.server.http.HttpServer
import org.slf4j.event.Level

internal class BemKtorHttpServer(
    override val host: String,
    override val port: Int,
    private val service: FactsService,
) : HttpServer {
    override fun create() {
        engine = embeddedServer(
            factory = CIO,
            host = host,
            port = port
        ) {
            install(CallLogging) {
                level = Level.INFO
            }
            routing {
                route(Api.BASE_PATH) {
                    route(HttpProtocol.VERSION) {
                        post(Endpoints.Facts.path) {
                            val body = call.receiveText()

                            val decoder = JsonCodec(Json {
                                ignoreUnknownKeys = true
                            })

                            service.publish(fact = decoder.decode(body))

                            call.respond(HttpStatusCode.OK)
                        }
                    }
                }
            }
        }
    }

    override fun start() { engine.start(wait = false) }

    override fun stop() { engine.stop() }

    override fun destroy() { }

    private lateinit var engine:
            EmbeddedServer<CIOApplicationEngine, CIOApplicationEngine.Configuration>
}
