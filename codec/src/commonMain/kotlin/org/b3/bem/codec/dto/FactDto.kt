package org.b3.bem.codec.dto

import kotlinx.serialization.Serializable
import kotlin.uuid.Uuid

@Serializable
sealed interface FactDto {
    val id: Uuid
    val timestamp: String
}
