package org.b3.bem.codec.dto

import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlSerialName
import kotlin.uuid.Uuid

@Serializable
@XmlSerialName("CompositeFact")
data class CompositeFactDto(
    override val id: Uuid,
    override val timestamp: String,
    val context: ContextDto,
    val facts: List<FactDto>,
) : FactDto
