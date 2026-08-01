package org.b3.bem.core.fact

import org.b3.bem.core.quantity.Quantity
import org.b3.bem.core.resource.Resource
import kotlin.time.Instant

data class Flow<Q : Quantity>(
    override val id: FactId,
    override val timestamp: Instant,
    val resource: Resource<Q>,
    val direction: Direction,
    val quantity: Q,
) : Fact
