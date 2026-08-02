package org.b3.bem.sdk.transport.file

import kotlinx.io.Sink
import org.b3.bem.sdk.transport.Transport

class FileTransport (
    private val output: Sink,
) : Transport<String> {
    override suspend fun send(data: String) {
        output.write(data.encodeToByteArray())
        output.flush()
    }
}
