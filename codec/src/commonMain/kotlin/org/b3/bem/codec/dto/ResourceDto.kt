package org.b3.bem.codec.dto

import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
@XmlSerialName("Resource")
data class ResourceDto(
    @XmlElement(true)
    val name: String,
)
