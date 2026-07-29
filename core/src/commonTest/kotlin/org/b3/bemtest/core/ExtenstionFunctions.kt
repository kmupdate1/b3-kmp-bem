package org.b3.bemtest.core

import org.b3.bem.core.event.BusinessEvent
import org.b3.bem.core.event.EventId
import org.b3.bem.core.resource.ResourceBinding
import org.b3.bem.core.quantity.Quantity
import kotlin.time.Instant

operator fun <Q : Quantity> ResourceBinding<Q>.plusAssign(delta: Q) {}

fun <Q : Quantity> ResourceBinding<Q>.event(
    at: Instant,
    delta: Q,
): BusinessEvent<Q> =
    BusinessEvent(
        id = EventId.gen(),
        time = at,
        resource = equipment,
        property = property,
        delta = delta,
    )
