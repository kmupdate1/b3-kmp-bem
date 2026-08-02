package org.b3.bem.server.storage.mongo

import org.b3.bem.model.dto.CompositeFactDto
import org.b3.bem.server.storage.FactRepository

class MongoFactRepository : FactRepository {
    override suspend fun store(fact: CompositeFactDto) {
        println("Stored fact: $fact")
    }
}
