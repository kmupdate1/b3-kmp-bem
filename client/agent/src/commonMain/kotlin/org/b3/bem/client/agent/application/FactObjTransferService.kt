package org.b3.bem.client.agent.application

import org.b3.bem.client.agent.http.FactClient
import org.b3.bem.client.agent.model.ObservationData
import org.b3.bem.model.mapper.toDto

class FactObjTransferService(
    private val pump1MeasurementService: Pump1MeasurementService,
    private val factClient: FactClient,
) {
    suspend operator fun invoke(data: ObservationData) {
        val fact = pump1MeasurementService(data)
        factClient.send(fact = fact.toDto())
    }
}
