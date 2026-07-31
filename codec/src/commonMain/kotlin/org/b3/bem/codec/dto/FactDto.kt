package org.b3.bem.codec.dto

import kotlinx.serialization.Serializable

@Serializable
data class FactDto(
    val context: ContextDto,
    val flows: List<FlowDto>,
)
