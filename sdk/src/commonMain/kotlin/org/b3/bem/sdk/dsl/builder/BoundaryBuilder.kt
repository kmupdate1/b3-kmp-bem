package org.b3.bem.sdk.dsl.builder

import org.b3.bem.core.fact.BoundaryContext
import org.b3.bem.core.fact.Fact
import org.b3.bem.core.fact.Flow

class BoundaryBuilder(scope: String) : FlowBuilder {
    val context = BoundaryContext(scope)

    override val flows: MutableList<Flow<*>> = mutableListOf()

    fun build(): Fact = Fact(
        context = context,
        flows = flows.toList(),
    )
}
