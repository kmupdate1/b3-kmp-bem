package org.b3.bem.core.resource

import org.b3.bem.core.quantity.Quantity

interface Resource<Q : Quantity> {
    val name: String
}
