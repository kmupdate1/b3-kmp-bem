package org.b3.bem.core.fact

import kotlin.jvm.JvmInline
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@JvmInline
value class FactId
private constructor(val value: Uuid) {
    companion object {
        @OptIn(ExperimentalUuidApi::class)
        fun gen(): FactId = FactId(value = Uuid.generateV7())
    }
}
