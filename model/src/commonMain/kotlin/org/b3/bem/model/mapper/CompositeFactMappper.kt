package org.b3.bem.model.mapper

import org.b3.bem.model.dto.CompositeFactDto
import org.b3.bem.core.fact.CompositeFact

fun CompositeFact.toDto(): CompositeFactDto =
    CompositeFactDto(
        id = id.value,
        timestamp = timestamp.toString(),
        context = context.toDto(),
        facts = facts.map { it.toDto() },
    )
