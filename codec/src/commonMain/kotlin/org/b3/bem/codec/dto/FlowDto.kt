package org.b3.bem.codec.dto

import kotlinx.serialization.Serializable

@Serializable
data class FlowDto(
    val resource: ResourceDto,
    val quantity: QuantityDto,
    val direction: String,
)
