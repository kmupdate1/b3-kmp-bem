package org.b3.bem.server.storage.mongo

import com.mongodb.kotlin.client.coroutine.MongoClient
import kotlinx.serialization.json.Json
import org.b3.bem.model.codec.json.JsonCodec
import org.b3.bem.model.dto.CompositeFactDto
import org.b3.bem.server.storage.FactRepository
import org.bson.Document

class MongoFactRepository : FactRepository {
    override suspend fun store(fact: CompositeFactDto) {
        val json = codec.encode(fact)
        collection.insertOne(Document.parse(json))
    }

    private val codec = JsonCodec(Json { ignoreUnknownKeys = true })
    private val collection = MongoClient.create("mongodb://127.0.0.1:27017")
        .getDatabase("business_event_management")
        .getCollection<Document>(collectionName = "facts")
}
