package org.b3.bem.core.quantity

import org.b3.bem.core.unit.ResourceUnit

interface Quantity {
    val value: Double
    val unit: ResourceUnit
}
