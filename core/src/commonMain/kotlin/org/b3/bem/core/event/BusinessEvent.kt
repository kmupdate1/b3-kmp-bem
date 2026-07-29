package org.b3.bem.core.event

import org.b3.bem.core.equipment.Equipment
import org.b3.bem.core.resource.Resource
import org.b3.bem.core.quantity.Quantity
import kotlin.time.Instant

data class BusinessEvent<Q : Quantity>(
    val id: EventId,
    val time: Instant,
    val resource: Equipment,
    val property: Resource<Q>,
    val delta: Q,
)
