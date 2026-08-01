package org.b3.bem.sdk.test

import io.ktor.client.engine.*
import io.ktor.client.engine.darwin.*

actual fun clientEngine(): HttpClientEngineFactory<HttpClientEngineConfig> = Darwin
