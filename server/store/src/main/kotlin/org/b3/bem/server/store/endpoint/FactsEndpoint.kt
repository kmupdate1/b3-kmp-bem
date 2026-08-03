package org.b3.bem.server.store.endpoint

import org.b3.bem.model.codec.Decoder
import org.b3.bem.protocol.http.Endpoints
import org.b3.bem.server.store.application.FactsService
import org.b3.ioe.http.HttpStatus
import org.b3.ioe.http.routing.routing

class FactsEndpoint(
    private val decoder: Decoder<String>,
    private val service: FactsService,
) {
    val routes = routing {
        path(Endpoints.Facts.path) {
            post {
                val body = receiveText()

                service.publish(decoder.decode(body))

                respond(HttpStatus.OK)
            }
        }
    }
}
