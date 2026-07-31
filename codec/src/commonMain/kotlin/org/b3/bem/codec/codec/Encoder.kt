package org.b3.bem.codec.codec

import org.b3.bem.codec.dto.FactDto

interface Encoder<T> {
    fun encode(dto: FactDto): T
}
