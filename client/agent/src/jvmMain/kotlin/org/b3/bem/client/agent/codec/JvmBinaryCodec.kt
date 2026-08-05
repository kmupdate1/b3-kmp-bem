package org.b3.bem.client.agent.codec

import org.b3.bem.client.agent.model.BinaryData
import org.b3.bem.client.agent.model.EquipmentCode
import org.b3.bem.client.agent.model.QuantityValue
import org.b3.bem.client.agent.model.ChannelCode
import java.nio.ByteBuffer
import java.nio.ByteOrder

class JvmBinaryCodec : BinaryCodec<BinaryData> {
    override fun encode(data: BinaryData): ByteArray =
        ByteBuffer
            .allocate(PACKET_SIZE)
            .order(ByteOrder.BIG_ENDIAN)
            .apply {
                put(data.equipment.value.toByte())
                put(data.channel.value.toByte())
                putDouble(data.quantity.value)
            }
            .array()

    override fun decode(data: ByteArray): BinaryData {
        require(data.size == PACKET_SIZE) {
            "Invalid packet size: ${data.size}"
        }

        val buffer = ByteBuffer
            .wrap(data)
            .order(ByteOrder.BIG_ENDIAN)

        return BinaryData(
            equipment = EquipmentCode.of(buffer.get().toUByte()),
            channel = ChannelCode.of(buffer.get().toUByte()),
            quantity = QuantityValue.of(buffer.getDouble()),
        )
    }

    private companion object {
        const val PACKET_SIZE = 10
    }
}
