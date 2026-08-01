package org.b3.bem.sdk.dsl.scope

import kotlinx.io.Sink

class DocumentPublishScope : PublishScope() {
    lateinit var output: Sink

    internal fun validate() {
        check(::output.isInitialized) { "output must be specified." }
    }
}
