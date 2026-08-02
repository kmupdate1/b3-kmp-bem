package org.b3.bem.model.codec

import org.b3.bem.model.dto.CompositeFactDto

interface Encoder<T> {
    fun encode(dto: CompositeFactDto): T
}
