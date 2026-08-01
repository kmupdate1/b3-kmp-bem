package org.b3.bem.sdk.dsl.function

import io.ktor.client.HttpClient
import org.b3.bem.sdk.transport.http.HttpTransport
import org.b3.bem.sdk.dsl.scope.HttpPublishScope
import org.b3.bem.sdk.format.Json
import org.b3.bem.sdk.publish.DefaultPublisher

suspend fun http(client: HttpClient, block: HttpPublishScope.() -> Unit) {
    val scope = HttpPublishScope()
        .apply(block)
        .also { it.validate() }

    val transport = HttpTransport(client = client, baseUrl = scope.url)

    val publisher = DefaultPublisher(
        format = Json,
        transport = transport,
    )

    scope.build().forEach { fact -> publisher.publish(fact) }
}
