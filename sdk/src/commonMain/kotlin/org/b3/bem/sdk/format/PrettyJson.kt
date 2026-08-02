package org.b3.bem.sdk.format

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.json.ClassDiscriminatorMode
import kotlinx.serialization.json.Json
import org.b3.bem.model.codec.Codec
import org.b3.bem.model.codec.json.JsonCodec

object PrettyJson : DocumentFormat<String> {
    @OptIn(ExperimentalSerializationApi::class)
    override val codec: Codec<String> = JsonCodec(Json {
        prettyPrint = true
        ignoreUnknownKeys = true
        classDiscriminatorMode = ClassDiscriminatorMode.POLYMORPHIC
        classDiscriminator = "type"
    })

    override fun document(parts: List<String>): String =
        parts.joinToString(
            prefix = "[\n",
            postfix = "\n]\n",
            separator = ",\n",
        ) { it.trim().indent() }
}
