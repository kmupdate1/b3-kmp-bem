package org.b3.bem.client.agent.application

import org.b3.bem.client.agent.codec.BinaryDecoder
import org.b3.bem.client.agent.model.BinaryData
import org.b3.ioe.udp.Datagram

class BinaryDatagramDecodeService(
    private val decoder: BinaryDecoder<BinaryData>,
    private val service: FactObjTransferService,
) {
    suspend operator fun invoke(datagram: Datagram) {
        val binary = decoder.decode(datagram.payload)
        service(binary)
    }
}
