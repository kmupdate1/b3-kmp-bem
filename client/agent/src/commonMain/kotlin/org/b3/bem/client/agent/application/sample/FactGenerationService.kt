package org.b3.bem.client.agent.application.sample

import org.b3.bem.client.agent.application.Pump1MeasurementService
import org.b3.bem.client.agent.model.ObservationData
import org.b3.bem.core.fact.Fact

class FactGenerationService(
    private val pump1MeasurementService: Pump1MeasurementService,
    private val takeWaterService: TakeWaterService,
    private val electricPowerGridService: ElectricPowerGridService,
    private val farmService: FarmService,
) {
    operator fun invoke(data: ObservationData): List<Fact> = buildList {
        val pump1Measured = pump1MeasurementService(data = data)

        val takeWater = takeWaterService(
            pump1Measured = pump1Measured,
        )

        val grid = electricPowerGridService(
            pump1Measured = pump1Measured,
            takeWater = takeWater,
        )

        val farm = farmService(
            grid = grid,
        )

        add(pump1Measured)
        add(takeWater)
        add(grid)
        add(farm)
    }
}
