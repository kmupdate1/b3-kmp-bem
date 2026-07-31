package org.b3.bem.sdk.publish.json

import org.b3.bem.codec.json.JsonCodec
import org.b3.bem.codec.mapper.toDto
import org.b3.bem.core.fact.Fact
import org.b3.bem.sdk.publish.PublishStrategy

object Json : PublishStrategy {
    override suspend fun publish(facts: List<Fact>) {
        facts.forEach { fact ->
            val dto = fact.toDto()
            JsonCodec.encode(dto)
        }
    }
}
