package org.b3.bem.sdk.dsl

import org.b3.bem.core.equipment.Equipment
import org.b3.bem.core.fact.Fact

fun boundary(scope: String, block: BoundaryBuilder.() -> Unit): Fact =
    BoundaryBuilder(scope = scope).apply(block).build()

fun <E : Equipment> E.measure(block: MeasurementBuilder<E>.(E) -> Unit): Fact =
    MeasurementBuilder(equipment = this).apply { block(equipment) }.build()
