package org.b3.bem.client.agent.model

import kotlin.jvm.JvmInline

@JvmInline
value class BinarySource private constructor(val source: String) {
    companion object {
        fun of(raw: String): BinarySource = BinarySource(source = raw)
    }
}
