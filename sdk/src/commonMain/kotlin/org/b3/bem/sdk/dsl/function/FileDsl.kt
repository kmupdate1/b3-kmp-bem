package org.b3.bem.sdk.dsl.function

import org.b3.bem.sdk.dsl.scope.FilePublishScope
import org.b3.bem.sdk.format.DocumentFormat
import org.b3.bem.sdk.format.Format
import org.b3.bem.sdk.publish.DocumentPublisher
import org.b3.bem.sdk.transport.file.ByteFileTransport
import org.b3.bem.sdk.transport.file.FileTransport

suspend fun fact(format: Format<String>, block: FilePublishScope.() -> Unit) { TODO() }

suspend fun binaryFact(format: Format<ByteArray>, block: FilePublishScope.() -> Unit) { TODO() }

suspend fun document(format: DocumentFormat<String>, block: FilePublishScope.() -> Unit) {
    val scope = FilePublishScope()
        .apply(block)
        .also { it.validate() }

    val transport = FileTransport(output = scope.output)

    DocumentPublisher(
        format = format,
        transport = transport,
    ).publish(scope.build())
}

suspend fun binaryDocument(format: DocumentFormat<ByteArray>, block: FilePublishScope.() -> Unit) {
    val scope = FilePublishScope()
        .apply(block)
        .also { it.validate() }

    val transport = ByteFileTransport(output = scope.output)

    DocumentPublisher(
        format = format,
        transport = transport,
    ).publish(scope.build())
}
