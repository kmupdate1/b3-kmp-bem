package org.b3.bem.sdk.dsl.function

import org.b3.bem.core.equipment.Equipment
import org.b3.bem.core.fact.CompositeFact
import org.b3.bem.sdk.dsl.builder.MeasurementBuilder

fun <E : Equipment> E.measure(block: MeasurementBuilder<E>.(E) -> Unit): CompositeFact =
    MeasurementBuilder(equipment = this).apply { block(equipment) }.build()
