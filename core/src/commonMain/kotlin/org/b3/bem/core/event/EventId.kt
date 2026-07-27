package org.b3.bem.core.event

import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@JvmInline
value class EventId
private constructor(private val value: Uuid) {
    companion object {
        @OptIn(ExperimentalUuidApi::class)
        fun gen() = EventId(value = Uuid.generateV7())
    }
}
