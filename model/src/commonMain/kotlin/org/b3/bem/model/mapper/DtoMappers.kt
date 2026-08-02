package org.b3.bem.model.mapper

import org.b3.bem.model.dto.EquipmentDto
import org.b3.bem.model.dto.QuantityDto
import org.b3.bem.model.dto.ResourceDto
import org.b3.bem.core.equipment.Equipment
import org.b3.bem.core.quantity.Quantity
import org.b3.bem.core.resource.Resource

fun Quantity.toDto(): QuantityDto = QuantityDto(delta = value, unit = unit.symbol)
fun Equipment.toDto(): EquipmentDto = EquipmentDto(id = id.value, name = name)
fun <Q : Quantity> Resource<Q>.toDto(): ResourceDto = ResourceDto(name = name)
