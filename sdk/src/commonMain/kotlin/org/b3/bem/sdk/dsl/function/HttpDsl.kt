package org.b3.bem.sdk.dsl.function

import io.ktor.client.HttpClient
import org.b3.bem.sdk.transport.http.HttpTransport
import org.b3.bem.sdk.dsl.scope.HttpPublishScope
import org.b3.bem.sdk.format.Json
import org.b3.bem.sdk.publish.DefaultPublisher

suspend fun http(client: HttpClient, block: HttpPublishScope.() -> Unit) {
    val scope = HttpPublishScope().apply(block)

    val transport = HttpTransport(client = client)

    DefaultPublisher(
        format = Json,
        transport = transport,
    ).publish(scope.build())
}
