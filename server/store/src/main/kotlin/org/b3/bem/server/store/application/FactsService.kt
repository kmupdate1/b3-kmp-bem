package org.b3.bem.server.store.application

import org.b3.bem.model.dto.FactDto
import org.b3.bem.server.store.storage.FactRepository

class FactsService(
    private val repository: FactRepository,
) {
    suspend fun publish(fact: FactDto) {
        repository.store(fact = fact)
    }
}
