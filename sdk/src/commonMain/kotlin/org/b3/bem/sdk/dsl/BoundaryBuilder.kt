package org.b3.bem.sdk.dsl

import org.b3.bem.sdk.model.BoundaryContext
import org.b3.bem.sdk.model.Fact
import org.b3.bem.sdk.model.Flow

class BoundaryBuilder(scope: String) : FlowScope {
    val context = BoundaryContext(scope)

    override val flows: MutableList<Flow<*>> = mutableListOf()

    fun build(): Fact = Fact(
        context = context,
        flows = flows.toList(),
    )
}
