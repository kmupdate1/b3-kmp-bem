package org.b3.bem.sdk.dsl

import org.b3.bem.core.equipment.Equipment
import org.b3.bem.core.fact.Fact
import org.b3.bem.sdk.publish.PublishScope
import org.b3.bem.sdk.publish.PublishStrategy

fun boundary(scope: String, block: BoundaryBuilder.() -> Unit): Fact =
    BoundaryBuilder(scope = scope).apply(block).build()

fun <E : Equipment> E.measure(block: MeasurementBuilder<E>.(E) -> Unit): Fact =
    MeasurementBuilder(equipment = this).apply { block(equipment) }.build()

suspend fun publish(strategy: PublishStrategy, block: PublishScope.() -> Unit) {
    val scope = PublishScope().also(block)
    strategy.publish(scope.build())
}
