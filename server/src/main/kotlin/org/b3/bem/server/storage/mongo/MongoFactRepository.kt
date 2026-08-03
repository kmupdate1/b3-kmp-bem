package org.b3.bem.server.storage.mongo

import org.b3.bem.model.codec.Codec
import org.b3.bem.model.dto.CompositeFactDto
import org.b3.bem.server.storage.FactRepository

class MongoFactRepository(
    private val codec: Codec<String>,
    private val database: MongoDatabase,
) : FactRepository {
    override suspend fun store(fact: CompositeFactDto) {
        collection.insert(codec.encode(fact))
    }

    private val collection = database.collection(COLLECTION)

    private companion object {
        const val COLLECTION = "Facts"
    }
}
