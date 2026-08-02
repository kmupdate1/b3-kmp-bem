package org.b3.bem.model.dto

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.protobuf.ProtoNumber
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName
import kotlin.uuid.Uuid

@Serializable
@SerialName("Equipment")
@XmlSerialName("Equipment")
@OptIn(ExperimentalSerializationApi::class)
data class EquipmentDto(
    @ProtoNumber(1)
    val id: Uuid,
    @ProtoNumber(2)
    @XmlElement(true)
    val name: String,
)
