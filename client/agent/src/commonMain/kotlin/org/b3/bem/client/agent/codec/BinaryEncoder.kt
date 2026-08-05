package org.b3.bem.client.agent.codec

interface BinaryEncoder<T> {
    fun encode(data: T): ByteArray
}
