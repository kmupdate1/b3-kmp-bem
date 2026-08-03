package org.b3.bem.server.store

import org.b3.bem.model.codec.Codec
import org.b3.bem.server.store.application.FactsService
import org.b3.bem.server.store.endpoint.FactsEndpoint
import org.b3.bem.server.store.storage.mongo.MongoFactRepository
import org.b3.ioe.mongo.MongoDatabase

class StoreComponent(
    codec: Codec<String>,
    database: MongoDatabase,
) {
    private val repository = MongoFactRepository(
        encoder = codec,
        database = database,
    )

    private val service = FactsService(repository = repository)

    val endpoint = FactsEndpoint(
        decoder = codec,
        service = service,
    )
}
