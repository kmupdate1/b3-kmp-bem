package org.b3.bem.dsl.function

import org.b3.bem.dsl.scope.DocumentPublishScope
import org.b3.bem.dsl.scope.FactPublishScope
import org.b3.bem.sdk.format.DocumentFormat
import org.b3.bem.sdk.format.Format
import org.b3.bem.sdk.publish.DefaultPublisher
import org.b3.bem.sdk.publish.DocumentPublisher
import org.b3.bem.sdk.transport.file.ByteFileTransport
import org.b3.bem.sdk.transport.file.FileTransport

suspend fun fact(format: Format<String>, block: FactPublishScope.() -> Unit) {
    val scope = FactPublishScope()
        .apply(block)
        .also { it.validate() }

    scope.build().forEach { fact ->
        val output = scope.outputFor(fact)

        try {
            DefaultPublisher(
                format = format,
                transport = FileTransport(output = output),
            ).publish(fact)
        } finally {
            output.close()
        }
    }
}

suspend fun document(format: DocumentFormat<String>, block: DocumentPublishScope.() -> Unit) {
    val scope = DocumentPublishScope()
        .apply(block)
        .also { it.validate() }

    val transport = FileTransport(output = scope.output)

    DocumentPublisher(
        format = format,
        transport = transport,
    ).publish(scope.build())
}

suspend fun binaryFact(format: Format<ByteArray>, block: FactPublishScope.() -> Unit) {
    val scope = FactPublishScope()
        .apply(block)
        .also { it.validate() }

    scope.build().forEach { fact ->
        val output = scope.outputFor(fact)

        try {
            DefaultPublisher(
                format = format,
                transport = ByteFileTransport(output = output),
            ).publish(fact)
        } finally {
            output.close()
        }
    }
}

suspend fun binaryDocument(format: DocumentFormat<ByteArray>, block: DocumentPublishScope.() -> Unit) {
    val scope = DocumentPublishScope()
        .apply(block)
        .also { it.validate() }

    val transport = ByteFileTransport(output = scope.output)

    DocumentPublisher(
        format = format,
        transport = transport,
    ).publish(scope.build())
}
