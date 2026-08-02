package org.b3.bem.model.mapper

import org.b3.bem.model.dto.BoundaryContextDto
import org.b3.bem.model.dto.ContextDto
import org.b3.bem.model.dto.MeasurementContextDto
import org.b3.bem.core.fact.BoundaryContext
import org.b3.bem.core.fact.Context
import org.b3.bem.core.fact.MeasurementContext

fun Context.toDto(): ContextDto =
    when (this) {
        is BoundaryContext -> BoundaryContextDto(boundary = scope)
        is MeasurementContext<*> -> MeasurementContextDto(equipment = equipment.toDto())
    }
