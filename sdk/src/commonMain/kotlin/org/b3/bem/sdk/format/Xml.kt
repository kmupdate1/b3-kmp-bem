package org.b3.bem.sdk.format

import nl.adaptivity.xmlutil.core.XmlVersion
import nl.adaptivity.xmlutil.serialization.XML
import org.b3.bem.codec.codec.Codec
import org.b3.bem.codec.xml.XmlCodec

object Xml : DocumentFormat<String> {
    override val codec: Codec<String> = XmlCodec(XML.recommended_1_0 {
        indentString = "    "
        xmlVersion = XmlVersion.XML11
    })

    override fun document(parts: List<String>): String =
        buildString {
            append("""<?xml version="1.1"?>""")
            append('\n')
            append("<Document>\n")

            parts.forEach {
                append(
                    it.substringAfter("?>")
                        .trim()
                        .indent()
                )
                append('\n')
            }

            append("</Document>")
            append('\n')
        }
}
