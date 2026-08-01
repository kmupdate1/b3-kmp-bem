package org.b3.bem.codec.mapper

import org.b3.bem.codec.dto.BoundaryContextDto
import org.b3.bem.codec.dto.ContextDto
import org.b3.bem.codec.dto.EquipmentDto
import org.b3.bem.codec.dto.CompositeFactDto
import org.b3.bem.codec.dto.FactDto
import org.b3.bem.codec.dto.FlowDto
import org.b3.bem.codec.dto.MeasurementContextDto
import org.b3.bem.codec.dto.QuantityDto
import org.b3.bem.codec.dto.ResourceDto
import org.b3.bem.core.equipment.Equipment
import org.b3.bem.core.fact.BoundaryContext
import org.b3.bem.core.fact.Context
import org.b3.bem.core.fact.CompositeFact
import org.b3.bem.core.fact.Fact
import org.b3.bem.core.fact.Flow
import org.b3.bem.core.fact.MeasurementContext
import org.b3.bem.core.quantity.Quantity
import org.b3.bem.core.resource.Resource

fun Quantity.toDto(): QuantityDto = QuantityDto(delta = value)
fun Equipment.toDto(): EquipmentDto = EquipmentDto(id = id.value, name = name)
fun <Q : Quantity> Resource<Q>.toDto(): ResourceDto = ResourceDto(name = name)

fun Context.toDto(): ContextDto =
    when (this) {
        is BoundaryContext -> BoundaryContextDto(boundary = scope)
        is MeasurementContext<*> -> MeasurementContextDto(equipment = equipment.toDto())
    }

fun <Q : Quantity> Flow<Q>.toDto(): FlowDto =
    FlowDto(
        id = id.value,
        timestamp = timestamp.toString(),
        resource = resource.toDto(),
        quantity = quantity.toDto(),
        direction = direction.name,
    )

fun CompositeFact.toDto(): CompositeFactDto =
    CompositeFactDto(
        id = id.value,
        timestamp = timestamp.toString(),
        context = context.toDto(),
        facts = facts.map { it.toDto() },
    )

fun Fact.toDto(): FactDto =
    when (this) {
        is CompositeFact -> this.toDto()
        is Flow<*> -> this.toDto()
    }
