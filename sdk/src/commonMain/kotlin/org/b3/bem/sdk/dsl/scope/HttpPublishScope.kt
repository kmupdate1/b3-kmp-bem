package org.b3.bem.sdk.dsl.scope

import io.ktor.http.Url

class HttpPublishScope : PublishScope() {
    lateinit var url: Url

    internal fun validate() {
        check(::url.isInitialized) { "url must be specified." }
    }
}
