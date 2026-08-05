package org.b3.bem.client.agent.binary.udp

import org.b3.bem.client.agent.codec.BinaryDecoder
import org.b3.bem.client.agent.model.Binary
import org.b3.bem.client.agent.model.BinaryData
import org.b3.bem.client.agent.model.BinarySource
import org.b3.ioe.udp.Datagram

class UdpBinaryDecoder(
    private val decoder: BinaryDecoder<BinaryData>,
) {
    fun decode(datagram: Datagram): Binary =
        Binary(
            source = BinarySource.of(raw = datagram.source.toString()),
            data = decoder.decode(data = datagram.payload),
        )
}
