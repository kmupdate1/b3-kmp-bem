package org.b3.bem.sdk.transport.http

import io.ktor.http.*
import org.b3.bem.protocol.http.Api

internal class EndpointUrlBuilder private constructor(
    private val builder: URLBuilder,
) {
    companion object {
        fun baseUrl(url: Url): EndpointUrlBuilder =
            EndpointUrlBuilder(URLBuilder(url = url))
    }

    fun endpoint(location: String): EndpointUrlBuilder = apply {
        builder.encodedPath = joinPath(
            builder.encodedPath,
            Api.BASE_PATH,
            HttpProtocol.VERSION,
            location,
        )
    }

    fun build(): Url = builder.build()

    private fun joinPath(vararg paths: String): String =
        paths
            .map { it.trim('/') }
            .filter { it.isNotEmpty() }
            .joinToString("/", prefix = "/")
}
