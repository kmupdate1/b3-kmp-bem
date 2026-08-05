package org.b3.bem.client.agent.application

import org.b3.bem.client.agent.model.BinaryData
import org.b3.bem.core.fact.Fact
import org.b3.bem.dsl.function.measure
import org.b3.bem.generated.equipment.Pump1
import org.b3.bem.generated.extension.wh

class BinaryMeasurementService {
    operator fun invoke(binary: BinaryData): Fact =
        Pump1.measure {
            it.electric outflow binary.quantity.value.wh
        }
}
