package org.b3.bem.client.agent

import org.b3.bem.client.agent.http.ktor.DefaultFactClient
import org.b3.bem.model.codec.Codec
import org.b3.ioe.ktor.client.HttpClient

class AgentComponent(
    codec: Codec<String>,
    httpClient: HttpClient,
) {
    private val client = DefaultFactClient(
        encoder = codec,
        httpClient = httpClient,
    )
}
