package org.b3.bem.model.codec

import org.b3.bem.model.dto.FactDto

interface Encoder<T> {
    fun encode(dto: FactDto): T
}
