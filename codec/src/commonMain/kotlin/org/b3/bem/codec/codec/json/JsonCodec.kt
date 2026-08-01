package org.b3.bem.codec.codec.json

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.ClassDiscriminatorMode
import kotlinx.serialization.json.Json
import org.b3.bem.codec.codec.Codec
import org.b3.bem.codec.dto.CompositeFactDto
import org.b3.bem.codec.dto.FactDto

class JsonCodec(
    private val json: Json,
) : Codec<String> {
    override fun encode(dto: CompositeFactDto): String = json.encodeToString(dto)
    override fun decode(data: String): FactDto = json.decodeFromString(data)
}
