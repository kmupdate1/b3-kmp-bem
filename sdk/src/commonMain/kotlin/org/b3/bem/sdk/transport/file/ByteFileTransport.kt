package org.b3.bem.sdk.transport.file

import kotlinx.io.Sink
import org.b3.bem.sdk.transport.Transport

class ByteFileTransport internal constructor(
    private val output: Sink,
) : Transport<ByteArray> {
    override suspend fun send(data: ByteArray) {
        output.write(data)
        output.flush()
    }
}
