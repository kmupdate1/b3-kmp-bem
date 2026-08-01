package org.b3.bem.codec.dto

import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName
import kotlin.uuid.Uuid

@Serializable
@XmlSerialName("Equipment")
data class EquipmentDto(
    val id: Uuid,
    @XmlElement(true)
    val name: String,
)
