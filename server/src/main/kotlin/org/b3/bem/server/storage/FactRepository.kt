package org.b3.bem.server.storage

import org.b3.bem.model.dto.CompositeFactDto

interface FactRepository {
    suspend fun store(fact: CompositeFactDto)
}
