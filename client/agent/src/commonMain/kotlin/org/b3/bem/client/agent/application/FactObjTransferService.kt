package org.b3.bem.client.agent.application

import org.b3.bem.client.agent.http.FactClient
import org.b3.bem.client.agent.model.BinaryData
import org.b3.bem.model.mapper.toDto

class FactObjTransferService(
    private val binaryMeasurementService: BinaryMeasurementService,
    private val factClient: FactClient,
) {
    suspend fun execute(binary: BinaryData) {
        val fact = binaryMeasurementService(binary = binary)
        factClient.send(fact = fact.toDto())
    }
}
