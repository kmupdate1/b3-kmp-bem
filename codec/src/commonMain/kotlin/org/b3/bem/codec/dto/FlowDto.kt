package org.b3.bem.codec.dto

import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlElement
import nl.adaptivity.xmlutil.serialization.XmlSerialName
import kotlin.uuid.Uuid

@Serializable
@XmlSerialName("Flow")
data class FlowDto(
    override val id: Uuid,
    override val timestamp: String,
    val resource: ResourceDto,
    val quantity: QuantityDto,
    @XmlElement(true)
    val direction: String,
) : FactDto
