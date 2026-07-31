package org.b3.bem.sdk.transport

interface Transport<T> {
    suspend fun send(data: T)
}
