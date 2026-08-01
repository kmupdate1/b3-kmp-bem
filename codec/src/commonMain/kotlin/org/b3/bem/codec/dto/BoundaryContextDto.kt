package org.b3.bem.codec.dto

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.protobuf.ProtoNumber
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
@SerialName("BoundaryContext")
@XmlSerialName("BoundaryContext")
@OptIn(ExperimentalSerializationApi::class)
data class BoundaryContextDto(
    @ProtoNumber(1)
    @XmlElement(true)
    val boundary: String,
) : ContextDto
