package org.b3.bem.sdk.format

import org.b3.bem.codec.codec.Codec
import org.b3.bem.codec.codec.json.JsonCodec

object Json : DocumentFormat<String> {
    override val codec: Codec<String> = JsonCodec

    override fun document(parts: List<String>): String =
        parts.joinToString(
            prefix = "[\n",
            postfix = "\n]\n",
            separator = ",\n",
        ) { it.trim().indent() }
}
