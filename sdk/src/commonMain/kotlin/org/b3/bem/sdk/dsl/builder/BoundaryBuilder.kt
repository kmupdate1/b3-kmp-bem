package org.b3.bem.sdk.dsl.builder

import org.b3.bem.core.equipment.Equipment
import org.b3.bem.core.fact.FactId
import org.b3.bem.core.fact.BoundaryContext
import org.b3.bem.core.fact.CompositeFact
import kotlin.time.Clock

class BoundaryBuilder(private val scope: String) {
    fun <E : Equipment> E.measure(
        block: MeasurementBuilder<E>.(E) -> Unit,
    ) {
        measurements +=
            MeasurementBuilder(equipment = this, timestamp = timestamp)
                .apply { block(equipment) }
                .build()
    }

    internal fun build(): CompositeFact = CompositeFact(
        id = FactId.gen(),
        timestamp = timestamp,
        context = BoundaryContext(scope),
        facts = measurements,
    )

    private val timestamp = Clock.System.now()
    private val measurements = mutableListOf<CompositeFact>()
}
