package org.b3.bem.sdk.dsl.function

import org.b3.bem.core.equipment.Equipment
import org.b3.bem.core.fact.Fact
import org.b3.bem.sdk.dsl.builder.MeasurementBuilder

fun <E : Equipment> E.measure(block: MeasurementBuilder<E>.(E) -> Unit): Fact =
    MeasurementBuilder(equipment = this).apply { block(equipment) }.build()
