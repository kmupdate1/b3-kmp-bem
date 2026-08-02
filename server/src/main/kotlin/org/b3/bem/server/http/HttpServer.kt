package org.b3.bem.server.http

internal interface HttpServer {
    val host: String
    val port: Int

    fun create()
    fun start()
    fun stop()
    fun destroy()
}
