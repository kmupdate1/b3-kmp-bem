package org.b3.bem.server.storage.mongo

import com.mongodb.kotlin.client.coroutine.MongoDatabase
import org.bson.Document

class MongoDatabase internal constructor(
    private val database: MongoDatabase,
) {
    fun collection(name: String): MongoDocumentCollection =
        MongoDocumentCollection(
            database.getCollection<Document>(name)
        )
}
