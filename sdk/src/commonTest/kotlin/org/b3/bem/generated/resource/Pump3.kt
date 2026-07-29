package org.b3.bem.generated.resource

import kotlin.String
import org.b3.bem.core.resource.Resource
import org.b3.bem.core.equipment.Equipment
import org.b3.bem.core.equipment.EquipmentId
import org.b3.bem.generated.`property`.Electric
import org.b3.bem.generated.`property`.Water
import org.b3.bem.generated.quantity.ElectricQuantity
import org.b3.bem.generated.quantity.WaterQuantity

public object Pump3 : Equipment {
  override val name: String = "pump3"

  override val id: EquipmentId = EquipmentId.gen()

  public val electric: Resource<ElectricQuantity> = Electric

  public val water: Resource<WaterQuantity> = Water
}
