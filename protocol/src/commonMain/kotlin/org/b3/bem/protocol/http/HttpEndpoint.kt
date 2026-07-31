package org.b3.bem.protocol.http

import kotlin.jvm.JvmInline

@JvmInline
value class HttpEndpoint private constructor(val path: String) {
    companion object {
        fun path(path: String): HttpEndpoint = HttpEndpoint(path = path)
    }
}
