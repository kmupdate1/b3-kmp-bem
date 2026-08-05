package org.b3.bem.client.agent.model

import kotlin.jvm.JvmInline

@JvmInline
value class DirectionCode private constructor(val value: UByte) {
    companion object {
        fun of(raw: UByte): DirectionCode = DirectionCode(value = raw)
    }
}
