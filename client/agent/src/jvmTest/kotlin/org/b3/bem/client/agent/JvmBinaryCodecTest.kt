package org.b3.bem.client.agent

import org.b3.bem.client.agent.codec.JvmBinaryCodec
import org.b3.bem.client.agent.model.BinaryData
import org.b3.bem.client.agent.model.EquipmentCode
import org.b3.bem.client.agent.model.QuantityValue
import org.b3.bem.client.agent.model.ChannelCode
import org.junit.Test
import kotlin.test.assertEquals

class JvmBinaryCodecTest {
    @Test
    fun `encode and decode`() {
        val expected = BinaryData(
            equipment = EquipmentCode.of(1u),
            channel = ChannelCode.of(2u),
            quantity = QuantityValue.of(3.2),
        )

        val encoded = codec.encode(expected)
        val actual = codec.decode(encoded)

        assertEquals(expected, actual)
    }

    private val codec = JvmBinaryCodec()
}
