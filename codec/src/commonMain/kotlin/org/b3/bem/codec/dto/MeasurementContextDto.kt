package org.b3.bem.codec.dto

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import kotlinx.serialization.protobuf.ProtoNumber
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
@SerialName("MeasurementContext")
@XmlSerialName("MeasurementContext")
@OptIn(ExperimentalSerializationApi::class)
data class MeasurementContextDto(
    @ProtoNumber(1)
    val equipment: EquipmentDto,
) : ContextDto
