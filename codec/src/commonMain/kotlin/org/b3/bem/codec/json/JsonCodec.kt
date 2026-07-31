package org.b3.bem.codec.json

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.ClassDiscriminatorMode
import kotlinx.serialization.json.Json
import org.b3.bem.codec.codec.Codec
import org.b3.bem.codec.dto.FactDto

object JsonCodec : Codec<String> {
    override fun encode(dto: FactDto): String = json.encodeToString(dto)
    override fun decode(data: String): FactDto = json.decodeFromString(data)

    @OptIn(ExperimentalSerializationApi::class)
    private val json = Json {
        prettyPrint = true
        ignoreUnknownKeys = true
        classDiscriminatorMode = ClassDiscriminatorMode.NONE
    }
}
