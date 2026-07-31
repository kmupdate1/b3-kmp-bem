package org.b3.bem.sdk.publish

import org.b3.bem.core.fact.Fact

interface PublishStrategy {
    suspend fun publish(facts: List<Fact>)
}
