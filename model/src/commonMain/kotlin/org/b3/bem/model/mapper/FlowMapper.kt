package org.b3.bem.model.mapper

import org.b3.bem.model.dto.FlowDto
import org.b3.bem.core.fact.Flow
import org.b3.bem.core.quantity.Quantity

fun <Q : Quantity> Flow<Q>.toDto(): FlowDto =
    FlowDto(
        id = id.value,
        timestamp = timestamp.toString(),
        resource = resource.toDto(),
        quantity = quantity.toDto(),
        direction = direction.name,
    )
