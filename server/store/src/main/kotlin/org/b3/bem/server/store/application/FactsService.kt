package org.b3.bem.server.store.application

import org.b3.bem.model.dto.CompositeFactDto
import org.b3.bem.server.store.storage.FactRepository

class FactsService(
    private val repository: FactRepository,
) {
    suspend fun publish(fact: CompositeFactDto) {
        repository.store(fact = fact)
    }
}
