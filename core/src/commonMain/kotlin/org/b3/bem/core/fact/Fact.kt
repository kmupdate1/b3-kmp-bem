package org.b3.bem.core.fact

import kotlin.time.Instant

sealed interface Fact {
    val id: FactId
    val timestamp: Instant
}
