package org.b3.bem.sdk.transport.http

import io.ktor.client.*
import io.ktor.client.request.*
import io.ktor.http.*
import org.b3.bem.protocol.http.Endpoints
import org.b3.bem.sdk.transport.Transport

class HttpTransport (
    private val client: HttpClient,
    private val baseUrl: Url,
) : Transport<String> {
    override suspend fun send(data: String) {
        val url = EndpointUrlBuilder.build(baseUrl = baseUrl, endpoint = Endpoints.Facts.path)

        client.post(url = url) {
            contentType(ContentType.Application.Json)
            setBody(data)
        }
    }
}
