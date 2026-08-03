package org.b3.bem.server.storage.mongo

import com.mongodb.kotlin.client.coroutine.MongoCollection
import org.bson.Document

class MongoDocumentCollection(
    private val collection: MongoCollection<Document>,
) {
    suspend fun insert(data: String) {
        collection.insertOne(Document.parse(data))
    }
}
