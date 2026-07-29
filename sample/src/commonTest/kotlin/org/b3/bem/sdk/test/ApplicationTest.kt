package org.b3.bem.sdk.test

import org.b3.bem.generated.equipment.Battery1
import org.b3.bem.generated.equipment.Pump1
import org.b3.bem.generated.equipment.Pump2
import org.b3.bem.generated.equipment.Pump3
import org.b3.bem.sdk.dsl.boundary
import org.b3.bem.sdk.dsl.measure
import org.junit.Test

@Test
fun generateBem() {
    val measured = Pump1.measure {
        it.electric outflow 120.wh
        it.water outflow 500.m3
    }

    val grid = boundary("Grid") {
        Battery1.electric inflow 800.wh
        Battery1.electric outflow 200.wh
        Pump3.electric inflow 50.wh
        Pump3.electric outflow 50.wh
    }

    val takeWater = boundary("Take Water from river") {
        Pump2.run {
            electric inflow 600.wh
            electric outflow 500.wh
            water inflow 750.m3
            water outflow 730.m3
        }
    }
}
