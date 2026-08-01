package org.b3.bem.codec.dto

import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
@XmlSerialName("MeasurementContext")
data class MeasurementContextDto(
    val equipment: EquipmentDto,
) : ContextDto
