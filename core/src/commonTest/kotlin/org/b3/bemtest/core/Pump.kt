package org.b3.bemtest.core

import org.b3.bem.core.model.Resource
import org.b3.bem.core.model.ResourceId
import org.b3.bem.core.property.ResourceProperty
import org.b3.bem.core.quantity.Quantity

object Pump : Resource {
    override val id: ResourceId
        get() = TODO("Not yet implemented")
    override val name = "Pump"

    val energy = object : ResourceProperty<Quantity> {
        override val resource = this@Pump
        override val property = Energy
    }
}
