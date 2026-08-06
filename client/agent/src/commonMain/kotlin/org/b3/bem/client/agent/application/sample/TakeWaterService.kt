package org.b3.bem.client.agent.application.sample

import org.b3.bem.core.fact.Fact
import org.b3.bem.dsl.function.boundary
import org.b3.bem.generated.equipment.Pump2
import org.b3.bem.generated.extension.m3
import org.b3.bem.generated.extension.wh

class TakeWaterService {
    operator fun invoke(pump1Measured: Fact): Fact = boundary("Take Water From River") {
        add(pump1Measured)
        Pump2.measure {
            it.electric inflow 600.wh
            it.electric outflow 500.wh
            it.water inflow 750.m3
            it.water outflow 730.m3
        }
    }
}
