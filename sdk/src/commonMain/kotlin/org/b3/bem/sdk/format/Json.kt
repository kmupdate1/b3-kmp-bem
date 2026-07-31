package org.b3.bem.sdk.format

import org.b3.bem.codec.codec.Codec
import org.b3.bem.codec.json.JsonCodec

object Json : Format<String> {
    override val codec: Codec<String> = JsonCodec
}
