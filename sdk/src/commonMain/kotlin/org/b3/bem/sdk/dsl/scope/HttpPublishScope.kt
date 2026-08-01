package org.b3.bem.sdk.dsl.scope

import io.ktor.http.Url
import org.b3.bem.sdk.dsl.annotation.BemDsl

@BemDsl
class HttpPublishScope : PublishScope() {
    lateinit var url: Url

    internal fun validate() {
        check(::url.isInitialized) { "url must be specified." }
    }
}
