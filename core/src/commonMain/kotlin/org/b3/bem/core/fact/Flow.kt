package org.b3.bem.core.fact

import org.b3.bem.core.quantity.Quantity
import org.b3.bem.core.resource.Resource

data class Flow<Q : Quantity>(
    val resource: Resource<Q>,
    val direction: Direction,
    val quantity: Q,
) : Fact
