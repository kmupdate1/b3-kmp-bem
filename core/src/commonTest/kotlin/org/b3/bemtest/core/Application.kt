package org.b3.bemtest.core

import org.b3.bem.core.quantity.Quantity
import kotlin.time.Instant

fun main() {
    val event = Pump.energy.event(
        at = Instant.DISTANT_FUTURE,
        delta = object : Quantity { override val value = 580.0 },
    )
}
