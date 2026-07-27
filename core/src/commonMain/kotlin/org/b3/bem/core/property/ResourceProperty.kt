package org.b3.bem.core.property

import org.b3.bem.core.model.Resource
import org.b3.bem.core.quantity.Quantity

interface ResourceProperty<Q : Quantity> {
    val resource: Resource
    val property: Property<Q>
}
