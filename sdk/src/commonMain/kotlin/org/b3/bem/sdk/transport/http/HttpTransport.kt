package org.b3.bem.sdk.transport.http

import io.ktor.client.HttpClient
import io.ktor.client.request.*
import io.ktor.http.*
import org.b3.bem.protocol.http.Endpoints
import org.b3.bem.sdk.transport.Transport

class HttpTransport internal constructor(
    private val client: HttpClient,
    private val baseUrl: Url,
) : Transport<String> {
    override suspend fun send(data: String) {
        client.post {
            url.takeFrom(baseUrl)
            url.encodedPath = Endpoints.Facts.path

            contentType(ContentType.Application.Json)
            setBody(data)
        }
    }
}
