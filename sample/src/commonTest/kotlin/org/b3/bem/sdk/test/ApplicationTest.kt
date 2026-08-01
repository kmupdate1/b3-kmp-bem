package org.b3.bem.sdk.test

import io.ktor.client.*
import io.ktor.http.Url
import kotlinx.coroutines.runBlocking
import kotlinx.io.buffered
import kotlinx.io.files.Path
import kotlinx.io.files.SystemFileSystem
import org.b3.bem.generated.equipment.Battery1
import org.b3.bem.generated.equipment.Battery2
import org.b3.bem.generated.equipment.EmployeeA
import org.b3.bem.generated.equipment.Pump1
import org.b3.bem.generated.equipment.Pump2
import org.b3.bem.generated.equipment.Pump3
import org.b3.bem.generated.extension.kwh
import org.b3.bem.generated.extension.l
import org.b3.bem.generated.extension.m3
import org.b3.bem.generated.extension.month
import org.b3.bem.generated.extension.wh
import org.b3.bem.sdk.dsl.function.boundary
import org.b3.bem.sdk.dsl.function.document
import org.b3.bem.sdk.dsl.function.fact
import org.b3.bem.sdk.dsl.function.http
import org.b3.bem.sdk.dsl.function.measure
import org.b3.bem.sdk.format.Json
import org.b3.bem.sdk.format.Xml
import kotlin.test.Test

class ApplicationTest {

    @Test
    fun `user application sample execute test`() {
        val pump1Measured = Pump1.measure {
            it.electric outflow 120.wh
            it.water outflow 500.m3
        }

        val takeWater = boundary("Take Water from river") {
            Pump2.measure {
                it.electric inflow 600.wh
                it.electric outflow 500.wh
                it.water inflow 750.m3
                it.water outflow 730.m3
            }
        }

        val grid = boundary("Electric Power Grid") {
            Battery1.measure {
                it.electric inflow 800.wh
                it.electric outflow 200.wh
            }
            Pump3.measure {
                it.electric inflow 50.wh
                it.electric outflow 50.wh
            }
        }

        val farm = boundary("Off-Grid LABO Farm Play") {
            EmployeeA.measure {
                it.human inflow 1.5.month
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

        runBlocking {
            http(client = HttpClient(clientEngine()) {
                engine {}
            }) {
                url = Url("http://localhost:8080/facts")

                add(pump1Measured)
                add(takeWater)
                add(grid)
                add(farm)
            }

            document(format = Json) {
                output = SystemFileSystem.sink(Path("fact/documents/json/facts.json"))
                    .buffered()

                add(pump1Measured)
                add(takeWater)
                add(grid)
                add(farm)
            }

            document(format = Xml) {
                output = SystemFileSystem.sink(Path("fact/documents/xml/facts.xml"))
                    .buffered()

                add(pump1Measured)
                add(takeWater)
                add(grid)
                add(farm)
            }

            fact(format = Json) {
                outputFor = {
                    SystemFileSystem.sink(Path("fact/json/${it.id.value}.json"))
                        .buffered()
                }

                add(pump1Measured)
                add(takeWater)
                add(grid)
                add(farm)
            }

            fact(format = Xml) {
                outputFor = {
                    SystemFileSystem.sink(Path("fact/xml/${it.id.value}.xml"))
                        .buffered()
                }

                add(pump1Measured)
                add(takeWater)
                add(grid)
                add(farm)
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
