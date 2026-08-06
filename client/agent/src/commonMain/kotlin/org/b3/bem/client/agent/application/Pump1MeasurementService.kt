package org.b3.bem.client.agent.application

import org.b3.bem.client.agent.model.ObservationData
import org.b3.bem.core.fact.Fact
import org.b3.bem.dsl.function.measure
import org.b3.bem.generated.equipment.Pump1
import org.b3.bem.generated.extension.wh

class Pump1MeasurementService {
    operator fun invoke(data: ObservationData): Fact =
        Pump1.measure {
            it.electric outflow data.quantity.wh
        }
}
