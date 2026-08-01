package org.b3.bem.sdk.format

internal fun String.indent(size: Int = 4): String = prependIndent(" ".repeat(size))
