package org.b3.bem.codec.dto

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.protobuf.ProtoNumber
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
@SerialName("Resource")
@XmlSerialName("Resource")
@OptIn(ExperimentalSerializationApi::class)
data class ResourceDto(
    @ProtoNumber(1)
    @XmlElement(true)
    val name: String,
)
