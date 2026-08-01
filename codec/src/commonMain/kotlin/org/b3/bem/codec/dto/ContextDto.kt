package org.b3.bem.codec.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import nl.adaptivity.xmlutil.serialization.XmlSerialName

@Serializable
@SerialName("Context")
@XmlSerialName("Context")
sealed interface ContextDto
