package org.b3.bem.core.util

import java.security.MessageDigest

internal actual fun sha256(text: String): ByteArray =
    sha256(text.encodeToByteArray())

internal actual fun sha256(data: ByteArray): ByteArray =
    MessageDigest
        .getInstance("SHA-256")
        .digest(data)
