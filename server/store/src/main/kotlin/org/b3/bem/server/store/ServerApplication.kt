package org.b3.bem.server.store

import org.b3.runtime.RuntimeLauncher

object ServerApplication {
    @JvmStatic
    fun main(args: Array<String>) =
        RuntimeLauncher.launch(application = Server(args = args))
}
