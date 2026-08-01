package org.b3.bem.codec.dto

import kotlinx.serialization.Serializable
import kotlin.uuid.Uuid

@Serializable
data class CompositeFactDto(
    override val id: Uuid,
    override val timestamp: String,
    val context: ContextDto,
    val facts: List<FactDto>,
) : FactDto
