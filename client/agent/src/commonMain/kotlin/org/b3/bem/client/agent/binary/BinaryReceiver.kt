package org.b3.bem.client.agent.binary

import org.b3.bem.client.agent.model.Binary

interface BinaryReceiver {
    suspend fun receive(): Binary
}
