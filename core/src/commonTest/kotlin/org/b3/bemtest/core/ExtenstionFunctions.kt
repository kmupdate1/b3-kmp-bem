package org.b3.bemtest.core

import org.b3.bem.core.event.BusinessEvent
import org.b3.bem.core.event.EventId
import org.b3.bem.core.property.ResourceProperty
import org.b3.bem.core.quantity.Quantity
import kotlin.time.Instant

operator fun <Q : Quantity> ResourceProperty<Q>.plusAssign(delta: Q) {}

fun <Q : Quantity> ResourceProperty<Q>.event(
    at: Instant,
    delta: Q,
): BusinessEvent<Q> =
    BusinessEvent(
        id = EventId.gen(),
        time = at,
        resource = resource,
        property = property,
        delta = delta,
    )
