package org.b3.bem.sdk.publish

import org.b3.bem.model.mapper.toDto
import org.b3.bem.core.fact.CompositeFact
import org.b3.bem.sdk.format.DocumentFormat
import org.b3.bem.sdk.transport.Transport

class DocumentPublisher<T>(
    private val format: DocumentFormat<T>,
    private val transport: Transport<T>,
) {
    suspend fun publish(facts: List<CompositeFact>) {
        val parts = facts.map { format.codec.encode(it.toDto()) }

        transport.send(format.document(parts))
    }
}
