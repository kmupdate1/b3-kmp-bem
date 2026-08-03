package org.b3.bem.server.storage.mongo

import com.mongodb.kotlin.client.coroutine.MongoClient

class MongoStore(
    private val client: MongoClient,
) {
    fun database(name: String): MongoDatabase =
        MongoDatabase(client.getDatabase(name))
}
