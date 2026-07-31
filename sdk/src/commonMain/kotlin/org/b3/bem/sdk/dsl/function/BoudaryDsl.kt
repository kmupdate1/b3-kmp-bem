package org.b3.bem.sdk.dsl.function

import org.b3.bem.core.fact.CompositeFact
import org.b3.bem.sdk.dsl.builder.BoundaryBuilder

fun boundary(scope: String, block: BoundaryBuilder.() -> Unit): CompositeFact =
    BoundaryBuilder(scope = scope).apply(block).build()
