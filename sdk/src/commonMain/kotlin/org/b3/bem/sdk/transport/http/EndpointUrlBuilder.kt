package org.b3.bem.sdk.transport.http

import io.ktor.http.URLBuilder
import io.ktor.http.Url
import io.ktor.http.encodedPath

internal object EndpointUrlBuilder {
    fun build(baseUrl: Url, endpoint: String): Url =
        URLBuilder(baseUrl).apply {
            encodedPath = "${baseUrl.encodedPath.trimEnd('/')}/${endpoint.trimStart('/')}"
        }.build()
}
