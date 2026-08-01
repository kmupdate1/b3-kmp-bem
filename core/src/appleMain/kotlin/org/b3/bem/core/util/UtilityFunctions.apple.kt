package org.b3.bem.core.util

import kotlinx.cinterop.ExperimentalForeignApi
import kotlinx.cinterop.addressOf
import kotlinx.cinterop.usePinned
import platform.CoreCrypto.CC_SHA256
import platform.CoreCrypto.CC_SHA256_DIGEST_LENGTH

internal actual fun sha256(text: String): ByteArray =
    sha256(text.encodeToByteArray())

@OptIn(ExperimentalForeignApi::class)
internal actual fun sha256(data: ByteArray): ByteArray {
    val digest = UByteArray(CC_SHA256_DIGEST_LENGTH)

    data.usePinned { input ->
        digest.usePinned { output ->
            CC_SHA256(
                input.addressOf(0),
                data.size.toUInt(),
                output.addressOf(0),
            )
        }
    }

    return digest.toByteArray()
}
