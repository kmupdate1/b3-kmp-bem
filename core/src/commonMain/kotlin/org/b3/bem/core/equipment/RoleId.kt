package org.b3.bem.core.equipment

import org.b3.bem.core.util.hashToUuid
import org.b3.bem.core.util.sha256
import kotlin.jvm.JvmInline
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@JvmInline
value class RoleId
private constructor(val value: Uuid) {
    companion object {
        @Deprecated(
            message = "Use hashed ID",
            replaceWith = ReplaceWith(
                expression = "fun from(name: String, group: String? = null): EquipmentId",
            ),
            level = DeprecationLevel.WARNING,
        )
        @OptIn(ExperimentalUuidApi::class)
        fun gen(): RoleId = RoleId(value = Uuid.generateV7())

        fun from(name: String, group: String? = null): RoleId {
            val key = group?.let { "$it/$name" } ?: name

            return RoleId(value = hashToUuid(sha256(key)))
        }
    }
}
