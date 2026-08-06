package org.b3.bem.client.agent.codec

import org.b3.bem.client.agent.model.ObservationData
import org.b3.bem.client.agent.model.EquipmentCode
import org.b3.bem.client.agent.model.QuantityValue
import org.b3.bem.client.agent.model.ChannelCode
import java.nio.ByteBuffer
import java.nio.ByteOrder

class JvmObservationCodec : BinaryCodec<ObservationData> {
    override fun encode(data: ObservationData): ByteArray =
        ByteBuffer
            .allocate(PACKET_SIZE)
            .order(ByteOrder.BIG_ENDIAN)
            .apply {
                put(data.equipment.value.toByte())
                put(data.channel.value.toByte())
                putDouble(data.quantity)
            }
            .array()

    override fun decode(data: ByteArray): ObservationData {
        require(data.size == PACKET_SIZE) {
            "Invalid packet size: ${data.size}"
        }

        val buffer = ByteBuffer
            .wrap(data)
            .order(ByteOrder.BIG_ENDIAN)

        return ObservationData(
            equipment = EquipmentCode.of(buffer.get().toUByte()),
            channel = ChannelCode.of(buffer.get().toUByte()),
            quantityValue = QuantityValue.of(buffer.getDouble()),
        )
    }

    private companion object {
        const val PACKET_SIZE = 10
    }
}
