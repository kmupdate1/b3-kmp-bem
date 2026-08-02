package org.b3.bem.model.codec

import org.b3.bem.model.dto.FactDto

interface Decoder<T> {
    fun decode(data: T): FactDto
}
