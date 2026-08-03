package org.b3.bem.server.http.endpoint

import kotlinx.serialization.json.Json
import org.b3.bem.model.codec.json.JsonCodec
import org.b3.bem.protocol.http.Endpoints
import org.b3.bem.server.application.FactsService
import org.b3.ioe.http.HttpStatus
import org.b3.ioe.http.routing.routing

class FactsEndpoint(
    private val service: FactsService,
) {
    val routes = routing {
        path(Endpoints.Facts.path) {
            post {
                val body = receiveText()

                val decoder = JsonCodec(Json {
                    ignoreUnknownKeys = true
                })

                service.publish(decoder.decode(body))

                respond(HttpStatus.OK)
            }
        }
    }
}
