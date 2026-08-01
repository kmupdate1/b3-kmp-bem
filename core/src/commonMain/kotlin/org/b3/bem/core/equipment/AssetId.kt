package org.b3.bem.core.equipment

import org.b3.bem.core.util.hashToUuid
import org.b3.bem.core.util.sha256
import kotlin.jvm.JvmInline
import kotlin.uuid.ExperimentalUuidApi
import kotlin.uuid.Uuid

@JvmInline
value class AssetId
private constructor(private val value: Uuid) {
    companion object {
        @Deprecated(
            message = "Use hashed ID",
            replaceWith = ReplaceWith(
                expression = "fun from(name: String, group: String? = null): AssetId",
            ),
            level = DeprecationLevel.WARNING,
        )
        @OptIn(ExperimentalUuidApi::class)
        fun gen(): AssetId = AssetId(value = Uuid.generateV7())

        fun from(name: String): AssetId =
            AssetId(value = hashToUuid(sha256(name)))
    }
}
