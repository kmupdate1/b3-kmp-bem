package org.b3.bem.codec.codec

import org.b3.bem.codec.dto.FactDto

interface Decoder<T> {
    fun decode(data: T): FactDto
}
