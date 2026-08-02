package org.b3.bem.sdk.test

import io.ktor.client.*
import io.ktor.http.*
import kotlinx.coroutines.runBlocking
import kotlinx.datetime.TimeZone
import kotlinx.datetime.toLocalDateTime
import kotlinx.io.buffered
import kotlinx.io.files.Path
import kotlinx.io.files.SystemFileSystem
import org.b3.bem.dsl.function.*
import org.b3.bem.generated.equipment.*
import org.b3.bem.generated.extension.*
import org.b3.bem.sdk.format.CompactJson
import org.b3.bem.sdk.format.PrettyJson
import org.b3.bem.sdk.format.Proto
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
            add(pump1Measured)
            Pump2.measure {
                it.electric inflow 600.wh
                it.electric outflow 500.wh
                it.water inflow 750.m3
                it.water outflow 730.m3
            }
        }

        val grid = boundary("Electric Power Grid") {
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
            }
        }

        val farm = boundary("Off-Grid LABO Farm Play") {
            add(grid)
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

                include(pump1Measured)
                include(takeWater)
                include(grid)
                include(farm)
            }

            document(format = CompactJson) {
                output = SystemFileSystem.sink(Path("fact/documents/json/facts.json"))
                    .buffered()

                include(pump1Measured)
                include(takeWater)
                include(grid)
                include(farm)
            }

            document(format = Xml) {
                output = SystemFileSystem.sink(Path("fact/documents/xml/facts.xml"))
                    .buffered()

                include(pump1Measured)
                include(takeWater)
                include(grid)
                include(farm)
            }

            val timeZone = TimeZone.of("Asia/Tokyo")

            fact(format = PrettyJson) {
                outputFor = { fact ->
                    val localDateTime = fact.timestamp
                        .toLocalDateTime(timeZone)

                    val directory = Path(
                        "fact/${localDateTime.year}/${localDateTime.month}/${localDateTime.day}/${fact.id.value}"
                    )

                    SystemFileSystem.createDirectories(directory)
                    SystemFileSystem.sink(Path(directory, "fact.json"))
                        .buffered()
                }

                include(pump1Measured)
                include(takeWater)
                include(grid)
                include(farm)
            }

            fact(format = Xml) {
                outputFor = { fact ->
                    val localDateTime = fact.timestamp
                        .toLocalDateTime(timeZone)

                    val directory = Path(
                        "fact/${localDateTime.year}/${localDateTime.month}/${localDateTime.day}/${fact.id.value}"
                    )

                    SystemFileSystem.createDirectories(directory)
                    SystemFileSystem.sink(Path(directory, "fact.xml"))
                        .buffered()
                }

                include(
                    pump1Measured,
                    takeWater,
                    grid,
                    farm,
                )
            }

            binaryFact(format = Proto) {
                outputFor = { fact ->
                    val localDateTime = fact.timestamp
                        .toLocalDateTime(timeZone)

                    val directory = Path(
                        "fact/${localDateTime.year}/${localDateTime.month}/${localDateTime.day}/${fact.id.value}"
                    )

                    SystemFileSystem.createDirectories(directory)
                    SystemFileSystem.sink(Path(directory, "fact.pb"))
                        .buffered()
                }

                include(farm)
            }

            /*
            mqtt(Proto) {
                add(grid)
                add(pump1Measured)
            }
            */
        }
    }
}
