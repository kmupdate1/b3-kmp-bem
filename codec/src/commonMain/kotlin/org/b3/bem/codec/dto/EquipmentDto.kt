package org.b3.bem.codec.dto

import kotlinx.serialization.Serializable
import kotlin.uuid.Uuid

@Serializable
data class EquipmentDto(
    val key: Uuid,
    val name: String,
)
