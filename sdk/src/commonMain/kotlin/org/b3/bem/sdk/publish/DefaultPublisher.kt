package org.b3.bem.sdk.publish

import org.b3.bem.codec.mapper.toDto
import org.b3.bem.core.fact.CompositeFact
import org.b3.bem.sdk.transport.Transport
import org.b3.bem.sdk.format.Format

class DefaultPublisher<T>(
    private val format: Format<T>,
    private val transport: Transport<T>,
) {
    suspend fun publish(facts: List<CompositeFact>) {
        facts.forEach { fact ->
            val dto = fact.toDto()
            val body = format.codec.encode(dto)

            transport.send(data = body)
        }
    }
}
