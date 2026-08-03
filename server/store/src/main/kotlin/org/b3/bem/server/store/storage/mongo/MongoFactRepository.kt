package org.b3.bem.server.store.storage.mongo

import org.b3.bem.model.codec.Encoder
import org.b3.bem.model.dto.CompositeFactDto
import org.b3.bem.server.store.storage.FactRepository
import org.b3.ioe.mongo.MongoDatabase

class MongoFactRepository(
    private val encoder: Encoder<String>,
    private val database: MongoDatabase,
) : FactRepository {
    override suspend fun store(fact: CompositeFactDto) {
        collection.insert(encoder.encode(fact))
    }

    private val collection = database.collection(COLLECTION)

    private companion object {
        const val COLLECTION = "facts"
    }
}
