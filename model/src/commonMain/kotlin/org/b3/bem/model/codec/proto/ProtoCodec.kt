package org.b3.bem.model.codec.proto

import kotlinx.serialization.ExperimentalSerializationApi
import kotlinx.serialization.decodeFromByteArray
import kotlinx.serialization.encodeToByteArray
import kotlinx.serialization.protobuf.ProtoBuf
import org.b3.bem.model.codec.Codec
import org.b3.bem.model.dto.CompositeFactDto

@OptIn(ExperimentalSerializationApi::class)
object ProtoCodec : Codec<ByteArray> {
    override fun encode(dto: CompositeFactDto): ByteArray = ProtoBuf.encodeToByteArray(dto)
    override fun decode(data: ByteArray): CompositeFactDto = ProtoBuf.decodeFromByteArray(data)
}
