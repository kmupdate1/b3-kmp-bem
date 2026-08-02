package org.b3.bem.server.storage

import org.b3.bem.model.dto.CompositeFactDto

internal interface FactRepository {
    suspend fun store(fact: CompositeFactDto)
}
