package org.b3.bem.client.agent.application

import org.b3.bem.client.agent.codec.BinaryDecoder
import org.b3.bem.client.agent.model.BinaryData

class BinaryDatagramDecodeService(
    private val decoder: BinaryDecoder<BinaryData>,
    private val service: FactObjTransferService,
) {
    suspend operator fun invoke(bytes: ByteArray) {
        val binary = decoder.decode(bytes)
        service(binary)
    }
}
