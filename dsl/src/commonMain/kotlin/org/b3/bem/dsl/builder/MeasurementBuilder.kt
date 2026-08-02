package org.b3.bem.dsl.builder

import org.b3.bem.core.equipment.Equipment
import org.b3.bem.core.fact.FactId
import org.b3.bem.core.fact.CompositeFact
import org.b3.bem.core.fact.MeasurementContext
import org.b3.bem.dsl.annotation.BemDsl
import kotlin.time.Instant

@BemDsl
class MeasurementBuilder<E : Equipment>(
    internal val equipment: E,
    internal val timestamp: Instant,
) : FlowBuilder(timestamp = timestamp) {
    val context = MeasurementContext(equipment)

    fun build(): CompositeFact = CompositeFact(
        id = FactId.gen(),
        timestamp = timestamp,
        context = context,
        facts = flows.toList(),
    )
}
