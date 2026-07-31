package org.b3.bem.sdk.format

import kotlinx.serialization.DeserializationStrategy
import kotlinx.serialization.SerializationStrategy
import kotlinx.serialization.StringFormat
import kotlinx.serialization.modules.SerializersModule
import org.b3.bem.codec.codec.Codec
import org.b3.bem.codec.xml.XmlCodec

object Xml : Format<String> {
    override val codec: Codec<String> = XmlCodec(object : StringFormat {
        override fun <T> encodeToString(serializer: SerializationStrategy<T>, value: T): String {
            TODO("Not yet implemented")
        }

        override fun <T> decodeFromString(deserializer: DeserializationStrategy<T>, string: String): T {
            TODO("Not yet implemented")
        }

        override val serializersModule: SerializersModule
            get() = TODO("Not yet implemented")
    })
}
