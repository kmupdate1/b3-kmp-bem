package org.b3.bem.sdk.dsl.scope

import kotlinx.io.Sink
import org.b3.bem.core.fact.CompositeFact

class FactPublishScope : PublishScope() {
    lateinit var output: (CompositeFact) -> Sink

    internal fun validate() {
        check(::output.isInitialized) { "directory must be specified." }
    }
}
