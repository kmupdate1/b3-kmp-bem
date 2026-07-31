package org.b3.bem.sdk.dsl.function

import org.b3.bem.sdk.dsl.scope.FilePublishScope
import org.b3.bem.sdk.format.Format
import org.b3.bem.sdk.publish.DefaultPublisher
import org.b3.bem.sdk.transport.file.ByteFileTransport
import org.b3.bem.sdk.transport.file.FileTransport

suspend fun file(format: Format<String>, block: FilePublishScope.() -> Unit) {
    val scope = FilePublishScope().apply(block)

    val transport = FileTransport(output = scope.output)

    DefaultPublisher(
        format = format,
        transport = transport,
    ).publish(scope.build())
}

suspend fun binaryFile(format: Format<ByteArray>, block: FilePublishScope.() -> Unit) {
    val scope = FilePublishScope().apply(block)

    val transport = ByteFileTransport(output = scope.output)

    DefaultPublisher(
        format = format,
        transport = transport,
    ).publish(scope.build())
}
