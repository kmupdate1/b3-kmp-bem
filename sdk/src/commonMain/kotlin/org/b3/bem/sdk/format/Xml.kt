package org.b3.bem.sdk.format

import nl.adaptivity.xmlutil.serialization.XML
import org.b3.bem.codec.codec.Codec
import org.b3.bem.codec.xml.XmlCodec

object Xml : Format<String> {
    override val codec: Codec<String> = XmlCodec(XML.recommended_1_0 {
        indentString = "    "
    })

    override fun document(parts: List<String>): String =
        buildString {
            append("""<?xml version="1.1"?>""")
            append("\n")
            append("<Facts>\n")

            parts.forEach {
                append(it.substringAfter("?>").trimStart())
                append('\n')
            }

            append("</Facts>")
        }
}
