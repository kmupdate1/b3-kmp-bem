package org.b3.bem.sdk.test

import io.ktor.client.*
import io.ktor.client.engine.cio.*
import kotlinx.coroutines.runBlocking
import org.b3.bem.generated.equipment.Battery1
import org.b3.bem.generated.equipment.Pump1
import org.b3.bem.generated.equipment.Pump2
import org.b3.bem.generated.equipment.Pump3
import org.b3.bem.generated.extension.m3
import org.b3.bem.generated.extension.wh
import org.b3.bem.sdk.dsl.function.boundary
import org.b3.bem.sdk.dsl.function.file
import org.b3.bem.sdk.dsl.function.http
import org.b3.bem.sdk.dsl.function.measure
import org.b3.bem.sdk.format.Xml

class ApplicationTest {

    fun `user application sample execute test`() {
        val pump1Measured = Pump1.measure {
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

        runBlocking {
            http(client = HttpClient(CIO) {
                engine {}
            }) {
                add(pump1Measured)
                add(grid)
            }

            file(format = Xml) {
                add(pump1Measured)
                add(takeWater)
            }

            /*
            binaryFile(format = Proto) {

            }

            mqtt(Proto) {
                add(grid)
                add(pump1Measured)
            }
            */
        }
    }
}
