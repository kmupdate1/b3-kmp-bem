package org.b3.bem.sdk.test

import io.ktor.client.engine.HttpClientEngineConfig
import io.ktor.client.engine.HttpClientEngineFactory
import io.ktor.client.engine.cio.CIO

actual fun clientEngine(): HttpClientEngineFactory<HttpClientEngineConfig> = CIO
