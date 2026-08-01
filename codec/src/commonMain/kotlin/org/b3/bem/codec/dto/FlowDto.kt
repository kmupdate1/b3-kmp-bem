package org.b3.bem.codec.dto

import kotlinx.serialization.Serializable
import kotlin.uuid.Uuid

@Serializable
data class FlowDto(
    override val id: Uuid,
    override val timestamp: String,
    val resource: ResourceDto,
    val quantity: QuantityDto,
    val direction: String,
) : FactDto
