package org.b3.bem.sdk.format

import org.b3.bem.codec.codec.Codec
import org.b3.bem.codec.codec.proto.ProtoCodec

object Proto : Format<ByteArray> {
    override val codec: Codec<ByteArray> = ProtoCodec
}
