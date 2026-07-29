package org.b3.bem.generated.resource

import kotlin.String
import org.b3.bem.core.resource.Resource
import org.b3.bem.core.equipment.Equipment
import org.b3.bem.core.equipment.EquipmentId
import org.b3.bem.generated.`property`.Human
import org.b3.bem.generated.quantity.HumanityQuantity

public object EmployeeA : Equipment {
  override val name: String = "employeeA"

  override val id: EquipmentId = EquipmentId.gen()

  public val human: Resource<HumanityQuantity> = Human
}
