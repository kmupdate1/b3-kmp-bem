package org.b3.bem.client.agent

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.cancel
import kotlinx.serialization.json.Json
import org.b3.bem.client.agent.application.*
import org.b3.bem.client.agent.codec.JvmBinaryCodec
import org.b3.bem.client.agent.http.ktor.DefaultFactClient
import org.b3.bem.model.codec.json.JsonCodec
import org.b3.bem.protocol.http.Api
import org.b3.bem.protocol.http.HttpProtocol
import org.b3.ioe.config.ConfigLoader
import org.b3.ioe.config.Parser
import org.b3.ioe.config.ktor.KtorConfig
import org.b3.ioe.config.udp.UdpConfig
import org.b3.ioe.ktor.client.KtorHttpClient
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

        val service = BinaryDatagramDecodeService(
            decoder = JvmBinaryCodec(),
            service = FactObjTransferService(
                binaryMeasurementService = BinaryMeasurementService(),
                factClient = DefaultFactClient(
                    encoder = JsonCodec(Json { ignoreUnknownKeys = true }),
                    httpClient = httpClient,
                ),
            ),
        )

        binaryServer = UdpServer(
            receiver = JvmUdpReceiver(port = udpConfig.port),
            scope = scope,
            handler = service::invoke,
        )

        httpClient.create()
    }

    override suspend fun onStart() {
        binaryServer.start()
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

    private val scope = CoroutineScope(SupervisorJob() + Dispatchers.Default)

    private lateinit var httpClient: KtorHttpClient
    private lateinit var binaryServer: UdpServer
}
