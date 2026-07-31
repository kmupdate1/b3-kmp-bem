package org.b3.bem.codec.mapper

import org.b3.bem.codec.dto.EquipmentDto
import org.b3.bem.codec.dto.FactDto
import org.b3.bem.codec.dto.QuantityDto
import org.b3.bem.codec.dto.ResourceDto
import org.b3.bem.core.equipment.Equipment
import org.b3.bem.core.fact.Fact
import org.b3.bem.core.quantity.Quantity
import org.b3.bem.core.resource.Resource

fun Quantity.toDto(): QuantityDto = QuantityDto(value = value)
fun Equipment.toDto(): EquipmentDto = EquipmentDto(businessKey = id.value, name = name)
fun <Q : Quantity> Resource<Q>.toDto(): ResourceDto = ResourceDto(name = name)
fun Fact.toDto(): FactDto = TODO()
