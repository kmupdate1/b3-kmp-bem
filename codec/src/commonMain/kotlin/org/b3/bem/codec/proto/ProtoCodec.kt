package org.b3.bem.codec.proto

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromByteArray
import kotlinx.serialization.encodeToByteArray
import kotlinx.serialization.protobuf.ProtoBuf
import org.b3.bem.codec.codec.Codec
import org.b3.bem.codec.dto.FactDto

@OptIn(ExperimentalSerializationApi::class)
object ProtoCodec : Codec<ByteArray> {
    override fun encode(dto: FactDto): ByteArray = ProtoBuf.encodeToByteArray(dto)
    override fun decode(data: ByteArray): FactDto = ProtoBuf.decodeFromByteArray(data)
}
