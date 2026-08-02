package org.b3.bem.dsl.function

import org.b3.bem.core.fact.CompositeFact
import org.b3.bem.dsl.builder.BoundaryBuilder

fun boundary(scope: String, block: BoundaryBuilder.() -> Unit): CompositeFact =
    BoundaryBuilder(scope = scope).apply(block).build()
