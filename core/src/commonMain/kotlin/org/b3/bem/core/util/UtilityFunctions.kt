package org.b3.bem.core.util

import kotlin.uuid.Uuid

internal expect fun sha256(text: String): ByteArray
internal expect fun sha256(data: ByteArray): ByteArray

internal fun hashToUuid(bytes: ByteArray): Uuid {
    require(bytes.size >= 16) {
        "At least 16 bytes are required to create a UUID."
    }

    val uuidBytes = bytes.copyOfRange(0, 16)

    // RFC 9562 / RFC 4122 Variant
    uuidBytes[8] = (uuidBytes[8].toInt() and 0x3F or 0x80).toByte()

    // Version 8
    uuidBytes[6] = (uuidBytes[6].toInt() and 0x0F or 0x80).toByte()

    return Uuid.fromByteArray(uuidBytes)
}
