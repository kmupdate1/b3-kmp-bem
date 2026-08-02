package org.b3.bem.model.codec.json

import kotlinx.serialization.json.Json
import org.b3.bem.model.codec.Codec
import org.b3.bem.model.dto.CompositeFactDto

class JsonCodec(
    private val json: Json,
) : Codec<String> {
    override fun encode(dto: CompositeFactDto): String = json.encodeToString(dto)
    override fun decode(data: String): CompositeFactDto = json.decodeFromString(data)
}
