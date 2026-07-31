package org.b3.bem.sdk.dsl.scope

import kotlinx.io.Sink

class FilePublishScope : PublishScope() {
    lateinit var output: Sink
}
