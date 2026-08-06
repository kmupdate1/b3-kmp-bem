package org.b3.bem.client.agent.model

data class ObservationData(
    val equipment: EquipmentCode,
    val channel: ChannelCode,
    private val quantityValue: QuantityValue,
) {
    val quantity get() = quantityValue.value
}
