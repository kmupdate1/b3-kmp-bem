package org.b3.bem.core.fact

import kotlin.time.Instant

data class CompositeFact(
    override val id: FactId,
    override val timestamp: Instant,
    val context: Context,
    val facts: List<Fact>,
) : Fact
