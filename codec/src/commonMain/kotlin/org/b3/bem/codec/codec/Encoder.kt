package org.b3.bem.codec.codec

import org.b3.bem.codec.dto.CompositeFactDto

interface Encoder<T> {
    fun encode(dto: CompositeFactDto): T
}
