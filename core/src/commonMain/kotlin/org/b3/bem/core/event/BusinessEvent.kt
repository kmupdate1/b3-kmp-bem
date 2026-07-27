package org.b3.bem.core.event

import org.b3.bem.core.model.Resource
import org.b3.bem.core.property.Property
import org.b3.bem.core.quantity.Quantity
import kotlin.time.Instant

data class BusinessEvent<Q : Quantity>(
    val id: EventId,
    val time: Instant,
    val resource: Resource,
    val property: Property<Q>,
    val delta: Q,
)
