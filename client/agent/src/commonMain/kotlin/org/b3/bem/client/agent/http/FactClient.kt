package org.b3.bem.client.agent.http

import org.b3.bem.model.dto.FactDto

interface FactClient {
    suspend fun send(fact: FactDto)
}
