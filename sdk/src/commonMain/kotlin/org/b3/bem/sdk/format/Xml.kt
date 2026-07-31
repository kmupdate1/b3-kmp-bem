package org.b3.bem.sdk.format

import nl.adaptivity.xmlutil.serialization.XML
import org.b3.bem.codec.codec.Codec
import org.b3.bem.codec.xml.XmlCodec

object Xml : Format<String> {
    override val codec: Codec<String> = XmlCodec(XML.recommended_1_0 {
        indentString = "  "
    })
}
