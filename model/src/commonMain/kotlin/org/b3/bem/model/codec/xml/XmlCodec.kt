package org.b3.bem.model.codec.xml

import kotlinx.serialization.StringFormat
import org.b3.bem.model.codec.Codec
import org.b3.bem.model.dto.CompositeFactDto

class XmlCodec(
    private val xml: StringFormat,
) : Codec<String> {
    override fun encode(dto: CompositeFactDto): String =
        xml.encodeToString(CompositeFactDto.serializer(), dto)

    override fun decode(data: String): CompositeFactDto =
        xml.decodeFromString(CompositeFactDto.serializer(), data)
}
