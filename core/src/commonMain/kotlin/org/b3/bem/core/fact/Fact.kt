package org.b3.bem.core.fact

import org.b3.bem.core.event.FactId
import kotlin.time.Instant

sealed interface Fact {
    val id: FactId
    val timestamp: Instant
}
