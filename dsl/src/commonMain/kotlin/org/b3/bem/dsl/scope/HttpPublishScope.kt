package org.b3.bem.dsl.scope

import io.ktor.http.Url
import org.b3.bem.dsl.annotation.BemDsl

@BemDsl
class HttpPublishScope : PublishScope() {
    lateinit var url: Url

    internal fun validate() {
        check(::url.isInitialized) { "url must be specified." }
    }
}
