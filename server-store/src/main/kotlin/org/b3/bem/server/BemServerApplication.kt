package org.b3.bem.server

import org.b3.runtime.jvm.RuntimeLauncher


object BemServerApplication {
    @JvmStatic
    fun main(args: Array<String>) =
        RuntimeLauncher.launch(application = BemServer(args = args))
}
