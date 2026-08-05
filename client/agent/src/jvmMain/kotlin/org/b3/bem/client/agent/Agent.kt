package org.b3.bem.client.agent

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.serialization.json.Json
import org.b3.bem.client.agent.application.*
import org.b3.bem.client.agent.codec.BinaryDecoder
import org.b3.bem.client.agent.codec.JvmBinaryCodec
import org.b3.bem.client.agent.http.ktor.DefaultFactClient
import org.b3.bem.client.agent.model.BinaryData
import org.b3.bem.model.codec.Codec
import org.b3.bem.model.codec.json.JsonCodec
import org.b3.bem.protocol.http.Api
import org.b3.bem.protocol.http.HttpProtocol
import org.b3.ioe.config.ConfigLoader
import org.b3.ioe.config.Parser
import org.b3.ioe.config.ktor.KtorConfig
import org.b3.ioe.config.udp.UdpConfig
import org.b3.ioe.ktor.client.KtorHttpClient
import org.b3.ioe.logging.logger
import org.b3.ioe.udp.JvmUdpReceiver
import org.b3.ioe.udp.UdpServer
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

        binaryReceiveService = BinaryReceiveService(
            binaryMeasurementService = BinaryMeasurementService(),
            factClient = factClient,
        )

        binaryDecoder = JvmBinaryCodec()
        binaryServer = UdpServer(
            receiver = JvmUdpReceiver(port = udpConfig.port),
            scope = scope,
            handler = { datagram ->
                logger.info("Agent handler started")

                try {
                    val binary = binaryDecoder.decode(datagram.payload)

                    logger.info("Binary decoded: $binary")

                    binaryReceiveService.execute(binary)

                    logger.info("Binary processed")
                } catch (e: Exception) {
                    logger.info("Binary processing failed: ${e.stackTraceToString()}")
                }
            },
        )

        factGenerationService = FactGenerationService(
            pump1MeasurementService = Pump1MeasurementService(),
            takeWaterService = TakeWaterService(),
            electricPowerGridService = ElectricPowerGridService(),
            farmService = FarmService(),
        )
    }

    override suspend fun onStart() {
        binaryServer.start()

        FactSendService(service = factGenerationService, client = factClient)
            //.execute()
    }

    override suspend fun onStop() {
        binaryServer.stop()
    }

    override suspend fun onDestroy() {
        scope.cancel()
        httpClient.destroy()
    }

    private val source = ConfigLoader.load(args = args)
    private val ktorConfig = Parser.parse<KtorConfig>(source = source)
    private val udpConfig = Parser.parse<UdpConfig>(source = source)

    private val codec: Codec<String> = JsonCodec(Json { ignoreUnknownKeys = true })
    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Default)

    private lateinit var httpClient: KtorHttpClient
    private lateinit var factClient: DefaultFactClient
    private lateinit var binaryServer: UdpServer
    private lateinit var binaryDecoder: BinaryDecoder<BinaryData>
    private lateinit var factGenerationService: FactGenerationService
    private lateinit var binaryReceiveService: BinaryReceiveService

    private val logger = logger(this::class.qualifiedName!!)
}
