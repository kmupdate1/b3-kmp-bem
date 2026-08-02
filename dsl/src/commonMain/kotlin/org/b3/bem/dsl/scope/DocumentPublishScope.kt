package org.b3.bem.dsl.scope

import kotlinx.io.Sink
import org.b3.bem.dsl.annotation.BemDsl

@BemDsl
class DocumentPublishScope : PublishScope() {
    lateinit var output: Sink

    internal fun validate() {
        check(::output.isInitialized) { "output must be specified." }
    }
}
