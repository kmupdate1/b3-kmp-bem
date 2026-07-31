package org.b3.bem.codec.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlin.uuid.Uuid

@Serializable
data class EquipmentDto(
    @SerialName("business_key")
    val businessKey: Uuid,
    val name: String,
)
