package org.b3.bem.model.codec.json

import kotlinx.serialization.json.Json
import org.b3.bem.model.codec.Codec
import org.b3.bem.model.dto.FactDto

class JsonCodec(
    private val json: Json,
) : Codec<String> {
    override fun encode(dto: FactDto): String = json.encodeToString(dto)
    override fun decode(data: String): FactDto = json.decodeFromString(data)
}
