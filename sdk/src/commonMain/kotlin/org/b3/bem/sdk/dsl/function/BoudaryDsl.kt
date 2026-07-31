package org.b3.bem.sdk.dsl.function

import org.b3.bem.core.fact.Fact
import org.b3.bem.sdk.dsl.builder.BoundaryBuilder

fun boundary(scope: String, block: BoundaryBuilder.() -> Unit): Fact =
    BoundaryBuilder(scope = scope).apply(block).build()
