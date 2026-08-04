package org.b3.bem.client.agent

import org.b3.runtime.RuntimeLauncher
import kotlin.jvm.JvmStatic

object AgentApplication {
    @JvmStatic
    fun main(args: Array<String>) =
        RuntimeLauncher.launch(application = Agent(args = args))
}
