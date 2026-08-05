package org.b3.bem.client.agent.codec

interface BinaryDecoder<T> {
    fun decode(data: ByteArray): T
}
