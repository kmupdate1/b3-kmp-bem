package org.b3.bem.sdk.dsl

import org.b3.bem.core.model.BoundaryContext
import org.b3.bem.core.model.Fact
import org.b3.bem.core.model.Flow

class BoundaryBuilder(scope: String) : FlowScope {
    val context = BoundaryContext(scope)

    override val flows: MutableList<Flow<*>> = mutableListOf()

    fun build(): Fact = Fact(
        context = context,
        flows = flows.toList(),
    )
}
