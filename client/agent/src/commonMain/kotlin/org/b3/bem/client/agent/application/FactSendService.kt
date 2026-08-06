package org.b3.bem.client.agent.application

import org.b3.bem.client.agent.application.sample.FactGenerationService
import org.b3.bem.client.agent.http.FactClient
import org.b3.bem.client.agent.model.ObservationData
import org.b3.bem.model.mapper.toDto

class FactSendService(
    private val service: FactGenerationService,
    private val client: FactClient,
) {
    suspend fun execute(data: ObservationData) {
        service(data = data)
            .forEach { fact -> client.send(fact = fact.toDto()) }
    }
}
