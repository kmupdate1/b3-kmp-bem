package org.b3.bem.codec.codec.xml

import kotlinx.serialization.StringFormat
import org.b3.bem.codec.codec.Codec
import org.b3.bem.codec.dto.CompositeFactDto
import org.b3.bem.codec.dto.FactDto

class XmlCodec(
    private val xml: StringFormat,
) : Codec<String> {
    override fun encode(dto: CompositeFactDto): String =
        xml.encodeToString(FactDto.serializer(), dto)

    override fun decode(data: String): FactDto =
        xml.decodeFromString(FactDto.serializer(), data)
}
