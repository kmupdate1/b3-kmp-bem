package org.b3.bem.sdk.publish

import org.b3.bem.codec.mapper.toDto
import org.b3.bem.core.fact.Fact
import org.b3.bem.sdk.format.Format
import org.b3.bem.sdk.transport.Transport

class DocumentPublisher<T>(
    private val format: Format<T>,
    private val transport: Transport<T>,
) {
    suspend fun publish(facts: List<Fact>) {
        val parts = facts.map { format.codec.encode(it.toDto()) }

        transport.send(format.document(parts))
    }
}
