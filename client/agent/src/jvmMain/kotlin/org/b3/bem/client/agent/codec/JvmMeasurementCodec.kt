package org.b3.bem.client.agent.codec

import org.b3.bem.client.agent.model.MeasurementData
import org.b3.bem.client.agent.model.ObservationData

class JvmMeasurementCodec(
    private val observationCodec: BinaryCodec<ObservationData>,
) : BinaryCodec<MeasurementData> {
    override fun encode(data: MeasurementData): ByteArray {
        require(data.observations.size <= UByte.MAX_VALUE.toInt())

        val observations = data.observations
            .map(observationCodec::encode)

        return byteArrayOf(data.observations.size.toByte()) +
                observations.fold(ByteArray(0), ByteArray::plus)
    }

    override fun decode(data: ByteArray): MeasurementData {
        require(data.isNotEmpty()) {
            "Measurement is empty"
        }

        val count = data[0].toUByte().toInt()

        require(data.size == HEADER_SIZE + count * OBSERVATION_SIZE) {
            "Invalid measurement size: ${data.size}"
        }

        val observations = (0 until count).map { index ->
            val offset = HEADER_SIZE + index * OBSERVATION_SIZE

            observationCodec.decode(
                data = data.copyOfRange(
                    offset,
                    offset + OBSERVATION_SIZE,
                ),
            )
        }

        return MeasurementData(
            observations = observations,
        )
    }

    private companion object {
        const val HEADER_SIZE = 1
        const val OBSERVATION_SIZE = 10
    }
}
