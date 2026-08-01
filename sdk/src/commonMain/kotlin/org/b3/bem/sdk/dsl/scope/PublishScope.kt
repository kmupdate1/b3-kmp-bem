package org.b3.bem.sdk.dsl.scope

import org.b3.bem.core.fact.CompositeFact
import org.b3.bem.sdk.dsl.annotation.BemDsl

@BemDsl
abstract class PublishScope {
    fun include(fact: CompositeFact) { compositeFacts += fact }
    fun include(vararg facts: CompositeFact) { compositeFacts += facts }

    internal fun build(): List<CompositeFact> = compositeFacts.toList()

    private val compositeFacts = mutableListOf<CompositeFact>()
}
