package org.b3.bem.client.agent.model

data class BinaryData(
    val equipment: EquipmentCode,
    val channel: ChannelCode,
    val quantity: QuantityValue,
)
