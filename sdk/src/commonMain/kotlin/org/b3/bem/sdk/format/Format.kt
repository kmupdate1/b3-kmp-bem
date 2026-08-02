package org.b3.bem.sdk.format

import org.b3.bem.model.codec.Codec

interface Format<T> {
    val codec: Codec<T>
}
