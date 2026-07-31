package org.b3.bem.sdk.dsl.builder

import org.b3.bem.core.quantity.Quantity
import org.b3.bem.core.resource.Resource
import org.b3.bem.core.fact.Direction
import org.b3.bem.core.fact.Flow

interface FlowBuilder {
    val flows: MutableList<Flow<*>>

    infix fun <Q : Quantity> Resource<Q>.inflow(quantity: Q) {
        flows += Flow(
            resource = this,
            direction = Direction.INFLOW,
            quantity = quantity,
        )
    }

    infix fun <Q : Quantity> Resource<Q>.outflow(quantity: Q) {
        flows += Flow(
            resource = this,
            direction = Direction.OUTFLOW,
            quantity = quantity,
        )
    }
}
