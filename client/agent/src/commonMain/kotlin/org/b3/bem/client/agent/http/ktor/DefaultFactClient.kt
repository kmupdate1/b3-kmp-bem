package org.b3.bem.client.agent.http.ktor

import org.b3.bem.client.agent.http.FactClient
import org.b3.bem.model.codec.Encoder
import org.b3.bem.model.dto.FactDto
import org.b3.bem.protocol.http.Endpoints
import org.b3.ioe.ktor.client.HttpClient

class DefaultFactClient(
    private val encoder: Encoder<String>,
    private val httpClient: HttpClient,
) : FactClient {
    override suspend fun send(fact: FactDto) {
        val body = encoder.encode(fact)
        val response = httpClient.post(path = Endpoints.Facts.path, body = body)

        check(response.status in 200..299) {
            "Failed to send fact: HTTP ${response.status}"
        }
    }
}
