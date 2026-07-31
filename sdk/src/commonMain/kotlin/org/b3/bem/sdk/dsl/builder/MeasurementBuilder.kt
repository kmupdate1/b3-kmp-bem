package org.b3.bem.sdk.dsl.builder

import org.b3.bem.core.equipment.Equipment
import org.b3.bem.core.fact.CompositeFact
import org.b3.bem.core.fact.Flow
import org.b3.bem.core.fact.MeasurementContext

class MeasurementBuilder<E : Equipment>(
    internal val equipment: E,
) : FlowBuilder() {
    val context = MeasurementContext(equipment)

    fun build(): CompositeFact = CompositeFact(
        context = context,
        facts = flows.toList(),
    )
}
