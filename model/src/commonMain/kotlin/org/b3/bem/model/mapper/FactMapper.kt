package org.b3.bem.model.mapper

import org.b3.bem.model.dto.FactDto
import org.b3.bem.core.fact.CompositeFact
import org.b3.bem.core.fact.Fact
import org.b3.bem.core.fact.Flow

fun Fact.toDto(): FactDto =
    when (this) {
        is CompositeFact -> this.toDto()
        is Flow<*> -> this.toDto()
    }
