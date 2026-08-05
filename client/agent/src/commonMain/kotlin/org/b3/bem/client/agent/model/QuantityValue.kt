package org.b3.bem.client.agent.model

import kotlin.jvm.JvmInline

@JvmInline
value class QuantityValue private constructor(val value: Double) {
    companion object {
        fun of(raw: Double): QuantityValue = QuantityValue(value = raw)
    }
}
