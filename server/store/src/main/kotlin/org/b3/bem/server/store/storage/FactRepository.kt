package org.b3.bem.server.store.storage

import org.b3.bem.model.dto.FactDto

interface FactRepository {
    suspend fun store(fact: FactDto)
}
