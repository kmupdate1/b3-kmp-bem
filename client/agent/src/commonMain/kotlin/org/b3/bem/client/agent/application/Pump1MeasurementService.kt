package org.b3.bem.client.agent.application

import org.b3.bem.core.fact.Fact
import org.b3.bem.dsl.function.measure
import org.b3.bem.generated.equipment.Pump1
import org.b3.bem.generated.extension.m3
import org.b3.bem.generated.extension.wh

class Pump1MeasurementService {
    operator fun invoke(): Fact =
        Pump1.measure {
            it.electric outflow 120.wh
            it.water outflow 500.m3
        }
}
