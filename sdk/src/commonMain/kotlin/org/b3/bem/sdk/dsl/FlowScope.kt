package org.b3.bem.sdk.dsl

import org.b3.bem.core.quantity.Quantity
import org.b3.bem.core.resource.Resource
import org.b3.bem.sdk.model.Direction
import org.b3.bem.sdk.model.Flow
import kotlin.collections.minusAssign
import kotlin.collections.plusAssign

interface FlowScope {
    val flows: MutableList<Flow<*>>

    infix fun <Q : Quantity> Resource<Q>.inflow(quantity: Q) {
        flows += Flow(
            resource = this,
            direction = Direction.INFLOW,
            quantity = quantity,
        )
    }

    infix fun <Q : Quantity> Resource<Q>.outflow(quantity: Q) {
        flows -= Flow(
            resource = this,
            direction = Direction.OUTFLOW,
            quantity = quantity,
        )
    }
}
