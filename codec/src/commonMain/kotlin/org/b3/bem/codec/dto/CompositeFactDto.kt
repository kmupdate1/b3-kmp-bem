package org.b3.bem.codec.dto

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.protobuf.ProtoNumber
import nl.adaptivity.xmlutil.serialization.XmlSerialName
import kotlin.uuid.Uuid

@Serializable
@SerialName("Fact")
@XmlSerialName("Fact")
@OptIn(ExperimentalSerializationApi::class)
data class CompositeFactDto(
    @ProtoNumber(1)
    override val id: Uuid,
    @ProtoNumber(2)
    override val timestamp: String,
    @ProtoNumber(3)
    val context: ContextDto,
    @ProtoNumber(4)
    val facts: List<FactDto>,
) : FactDto
