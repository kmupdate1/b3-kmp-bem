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
sealed interface FactDto {
    @ProtoNumber(1)
    val id: Uuid
    @ProtoNumber(2)
    val timestamp: String
}
