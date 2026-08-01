package org.b3.bem.codec.dto

import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
@XmlSerialName("Quantity")
data class QuantityDto(
    @XmlElement(true)
    val delta: Double,
    val unit: String,
)
