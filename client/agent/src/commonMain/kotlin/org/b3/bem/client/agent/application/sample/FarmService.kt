package org.b3.bem.client.agent.application.sample

import org.b3.bem.core.fact.Fact
import org.b3.bem.dsl.function.boundary
import org.b3.bem.generated.equipment.Battery2
import org.b3.bem.generated.equipment.EmployeeA
import org.b3.bem.generated.equipment.Pump1
import org.b3.bem.generated.extension.kwh
import org.b3.bem.generated.extension.l
import org.b3.bem.generated.extension.month
import org.b3.bem.generated.extension.wh

class FarmService {
    operator fun invoke(grid: Fact): Fact = boundary("Off-Grid LABO Farm Play") {
        add(grid)
        EmployeeA.measure {
            it.human outflow 1.5.month
        }
        Pump1.measure {
            it.electric outflow 1.2.kwh
            it.water outflow 500.l
        }
        Battery2.measure {
            it.electric inflow 800.wh
            it.electric outflow 300.wh
        }
    }
}
