package org.b3.bem.sdk.test

import io.ktor.client.engine.*

expect fun clientEngine(): HttpClientEngineFactory<HttpClientEngineConfig>
