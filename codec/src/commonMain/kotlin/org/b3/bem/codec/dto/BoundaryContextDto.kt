package org.b3.bem.codec.dto

import kotlinx.serialization.Serializable

@Serializable
data class BoundaryContextDto(
    val boundary: String,
) : ContextDto
