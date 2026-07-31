package org.b3.bem.codec.dto

import kotlinx.serialization.Serializable

@Serializable
data class MeasurementContextDto(
    val equipment: EquipmentDto,
) : ContextDto
