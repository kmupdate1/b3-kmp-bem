package org.b3.bem.core.fact

import org.b3.bem.core.event.FactId
import kotlin.time.Instant

data class CompositeFact(
    override val id: FactId,
    override val timestamp: Instant,
    val context: Context,
    val facts: List<Fact>,
) : Fact
