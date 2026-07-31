package org.b3.bem.sdk.format

import org.b3.bem.codec.codec.Codec

interface Format<T> {
    val codec: Codec<T>
    fun document(parts: List<T>): T
}
