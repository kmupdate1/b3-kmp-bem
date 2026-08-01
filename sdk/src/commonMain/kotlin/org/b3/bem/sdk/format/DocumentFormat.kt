package org.b3.bem.sdk.format

interface DocumentFormat<T> : Format<T> {
    fun document(parts: List<T>): T
}
