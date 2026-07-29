package org.b3.bem.core.resource

import org.b3.bem.core.equipment.Equipment
import org.b3.bem.core.quantity.Quantity

interface ResourceBinding<Q : Quantity> {
    val equipment: Equipment
    val property: Resource<Q>
}
