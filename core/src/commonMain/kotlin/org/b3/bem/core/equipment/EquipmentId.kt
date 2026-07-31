package org.b3.bem.core.equipment

import kotlin.jvm.JvmInline
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@JvmInline
value class EquipmentId
private constructor(val value: Uuid) {
    companion object {
        @OptIn(ExperimentalUuidApi::class)
        fun gen(): EquipmentId = EquipmentId(value = Uuid.generateV7())
    }
}
