package org.b3.bem.sdk.transport.http

import io.ktor.http.*
import org.b3.bem.protocol.http.Api

internal object EndpointUrlBuilder {
    fun build(baseUrl: Url, endpoint: String): Url =
        URLBuilder(baseUrl).apply {
            encodedPath = joinPath(
                baseUrl.encodedPath,
                Api.BASE_PATH,
                HttpProtocol.VERSION,
                endpoint,
            )
        }.build()

    private fun joinPath(vararg paths: String): String =
        paths
            .map { it.trim('/') }
            .filter { it.isNotEmpty() }
            .joinToString("/", prefix = "/")
}
