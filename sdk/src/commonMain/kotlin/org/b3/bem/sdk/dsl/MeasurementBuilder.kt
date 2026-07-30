package org.b3.bem.sdk.dsl

import org.b3.bem.core.equipment.Equipment
import org.b3.bem.core.fact.Fact
import org.b3.bem.core.fact.Flow
import org.b3.bem.core.fact.MeasurementContext

class MeasurementBuilder<E : Equipment>(
    internal val equipment: E,
) : FlowScope {
    val context = MeasurementContext(equipment)

    override val flows: MutableList<Flow<*>> = mutableListOf()

    fun build(): Fact = Fact(
        context = context,
        flows = flows.toList(),
    )
}
