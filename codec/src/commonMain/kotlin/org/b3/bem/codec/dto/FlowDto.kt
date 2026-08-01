package org.b3.bem.codec.dto

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.protobuf.ProtoNumber
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName
import kotlin.uuid.Uuid

@Serializable
@SerialName("Flow")
@XmlSerialName("Flow")
@OptIn(ExperimentalSerializationApi::class)
data class FlowDto(
    @ProtoNumber(1)
    override val id: Uuid,
    @ProtoNumber(2)
    override val timestamp: String,
    @ProtoNumber(3)
    val resource: ResourceDto,
    @ProtoNumber(4)
    val quantity: QuantityDto,
    @ProtoNumber(5)
    @XmlElement(true)
    val direction: String,
) : FactDto
