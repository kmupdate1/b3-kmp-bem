package org.b3.bem.client.agent.application

import org.b3.bem.client.agent.codec.BinaryDecoder
import org.b3.bem.client.agent.model.ObservationData

class BinaryDecodeService(
    private val decoder: BinaryDecoder<ObservationData>,
    private val factObjTransferService: FactObjTransferService,
) {
    suspend operator fun invoke(bytes: ByteArray) {
        val observationData = decoder.decode(bytes)
        factObjTransferService(observationData)
    }
}
