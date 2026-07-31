package org.b3.bem.sdk.dsl.scope

import org.b3.bem.core.fact.Fact

abstract class PublishScope {
    fun add(fact: Fact) { facts += fact }

    private val facts = mutableListOf<Fact>()
    internal fun build(): List<Fact> = facts.toList()
}
