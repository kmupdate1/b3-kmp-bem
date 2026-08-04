package org.b3.bem.client.agent

import kotlinx.serialization.json.Json
import org.b3.bem.client.agent.application.ElectricPowerGridService
import org.b3.bem.client.agent.application.FactGenerationService
import org.b3.bem.client.agent.application.FarmService
import org.b3.bem.client.agent.application.Pump1MeasurementService
import org.b3.bem.client.agent.application.TakeWaterService
import org.b3.bem.client.agent.http.ktor.DefaultFactClient
import org.b3.bem.model.codec.Codec
import org.b3.bem.model.codec.json.JsonCodec
import org.b3.bem.model.mapper.toDto
import org.b3.bem.protocol.http.Api
import org.b3.bem.protocol.http.HttpProtocol
import org.b3.ioe.config.ConfigLoader
import org.b3.ioe.config.Parser
import org.b3.ioe.config.ktor.KtorConfig
import org.b3.ioe.ktor.client.KtorHttpClient
import org.b3.runtime.lifecycle.Lifecycle

class Agent(
    private val args: Array<String>,
) : Lifecycle {
    override suspend fun onCreate() {
        httpClient = KtorHttpClient(
            baseUrl = "http://${ktorConfig.host}:${ktorConfig.port}${Api.BASE_PATH}/${HttpProtocol.VERSION}"
        )
        httpClient.create()

        factClient = DefaultFactClient(
            encoder = codec,
            httpClient = httpClient,
        )

        application = FactGenerationService(
            pump1MeasurementService = Pump1MeasurementService(),
            takeWaterService = TakeWaterService(),
            electricPowerGridService = ElectricPowerGridService(),
            farmService = FarmService(),
        )
    }

    override suspend fun onStart() {
        application()
            .map { it.toDto() }
            .forEach { dto -> factClient.send(fact = dto) }
    }

    override suspend fun onStop() { }

    override suspend fun onDestroy() {
        httpClient.destroy()
    }

    private val source = ConfigLoader.load(args = args)
    private val ktorConfig = Parser.parse<KtorConfig>(source = source)

    private val codec: Codec<String> = JsonCodec(Json { ignoreUnknownKeys = true })
    private lateinit var httpClient: KtorHttpClient
    private lateinit var factClient: DefaultFactClient
    private lateinit var application: FactGenerationService
}
