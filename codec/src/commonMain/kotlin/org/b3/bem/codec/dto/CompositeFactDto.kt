package org.b3.bem.codec.dto

import kotlinx.serialization.Serializable

@Serializable
data class CompositeFactDto(
    val context: ContextDto,
    val facts: List<FactDto>,
) : FactDto
