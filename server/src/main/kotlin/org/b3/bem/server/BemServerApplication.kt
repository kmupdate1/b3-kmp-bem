package org.b3.bem.server

import org.b3.runtime.core.jvm.RuntimeLauncher

object BemServerApplication {
    @JvmStatic
    fun main(args: Array<String>) =
        RuntimeLauncher.launch(
            application = BemServer(host = "127.0.0.1", port = 8000)
        )
}
