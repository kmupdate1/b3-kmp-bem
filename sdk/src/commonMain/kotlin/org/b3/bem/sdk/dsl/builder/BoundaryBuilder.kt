package org.b3.bem.sdk.dsl.builder

import org.b3.bem.core.equipment.Equipment
import org.b3.bem.core.fact.BoundaryContext
import org.b3.bem.core.fact.CompositeFact

class BoundaryBuilder(private val scope: String) {
    fun <E : Equipment> E.measure(
        block: MeasurementBuilder<E>.(E) -> Unit,
    ) {
        measurements +=
            MeasurementBuilder(this)
                .apply { block(equipment) }
                .build()
    }

    internal fun build(): CompositeFact = CompositeFact(
        context = BoundaryContext(scope),
        facts = measurements,
    )

    private val measurements = mutableListOf<CompositeFact>()
}
