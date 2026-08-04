package org.b3.bem.client.agent.application

import org.b3.bem.core.fact.Fact
import org.b3.bem.dsl.function.boundary
import org.b3.bem.generated.equipment.Battery1
import org.b3.bem.generated.equipment.Pump3
import org.b3.bem.generated.extension.m3
import org.b3.bem.generated.extension.wh

class ElectricPowerGridService {
    operator fun invoke(
        pump1Measured: Fact,
        takeWater: Fact,
    ): Fact = boundary("Electric Power Grid") {
        add(pump1Measured)
        boundary("Water Taking") {
            add(takeWater)
        }
        Battery1.measure {
            it.electric inflow 800.wh
            it.electric outflow 200.wh
        }
        Pump3.measure {
            it.electric inflow 50.wh
            it.electric outflow 50.wh
            it.water outflow 300.m3
        }
    }
}
