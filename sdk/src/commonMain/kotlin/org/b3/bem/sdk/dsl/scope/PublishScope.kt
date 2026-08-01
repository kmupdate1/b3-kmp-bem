package org.b3.bem.sdk.dsl.scope

import org.b3.bem.core.fact.CompositeFact
import org.b3.bem.sdk.dsl.annotation.BemDsl

@BemDsl
abstract class PublishScope {
    fun add(fact: CompositeFact) { facts += fact }

    private val facts = mutableListOf<CompositeFact>()
    internal fun build(): List<CompositeFact> = facts.toList()
}
