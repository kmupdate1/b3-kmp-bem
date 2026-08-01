package org.b3.bem.codec.dto

import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
@XmlSerialName("BoundaryContext")
data class BoundaryContextDto(
    @XmlElement(true)
    val boundary: String,
) : ContextDto
