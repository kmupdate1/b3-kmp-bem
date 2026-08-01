package org.b3.bem.sdk.dsl.scope

import kotlinx.io.Sink
import org.b3.bem.core.fact.CompositeFact
import org.b3.bem.sdk.dsl.annotation.BemDsl

@BemDsl
class FactPublishScope : PublishScope() {
    lateinit var outputFor: (CompositeFact) -> Sink

    internal fun validate() {
        check(::outputFor.isInitialized) { "directory must be specified." }
    }
}
