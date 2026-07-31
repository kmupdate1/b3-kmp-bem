package org.b3.bem.codec.json

import kotlinx.serialization.json.Json
import org.b3.bem.codec.codec.Codec
import org.b3.bem.codec.dto.FactDto

object JsonCodec : Codec<String> {
    override fun encode(dto: FactDto): String = json.encodeToString(dto)
    override fun decode(data: String): FactDto = json.decodeFromString(data)

    private val json = Json {
        prettyPrint = true
        ignoreUnknownKeys = true
    }
}
