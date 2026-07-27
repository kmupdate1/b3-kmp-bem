package org.b3.bem.core.property

import org.b3.bem.core.quantity.Quantity

interface Property<Q : Quantity> {
    val name: String
}
