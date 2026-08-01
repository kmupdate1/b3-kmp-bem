package org.b3.bem.codec.dto

import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlSerialName
import kotlin.uuid.Uuid

@Serializable
@XmlSerialName("Fact")
sealed interface FactDto {
    val id: Uuid
    val timestamp: String
}
