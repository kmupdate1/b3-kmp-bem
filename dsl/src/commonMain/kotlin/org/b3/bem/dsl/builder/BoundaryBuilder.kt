package org.b3.bem.dsl.builder

import org.b3.bem.core.equipment.Equipment
import org.b3.bem.core.fact.BoundaryContext
import org.b3.bem.core.fact.CompositeFact
import org.b3.bem.core.fact.Fact
import org.b3.bem.core.fact.FactId
import org.b3.bem.dsl.annotation.BemDsl
import kotlin.time.Clock

@BemDsl
class BoundaryBuilder(private val scope: String) {
    fun <E : Equipment> E.measure(
        block: MeasurementBuilder<E>.(E) -> Unit,
    ) {
        facts +=
            MeasurementBuilder(equipment = this, timestamp = timestamp)
                .apply { block(equipment) }
                .build()
    }

    fun boundary(
        scope: String,
        block: BoundaryBuilder.() -> Unit,
    ) {
        facts +=
            BoundaryBuilder(scope = scope)
                .apply(block)
                .build()
    }

    fun add(fact: Fact) { facts += fact }

    internal fun build(): CompositeFact = CompositeFact(
        id = FactId.gen(),
        timestamp = timestamp,
        context = BoundaryContext(scope),
        facts = facts,
    )

    private val timestamp = Clock.System.now()
    private val facts = mutableListOf<Fact>()
}
