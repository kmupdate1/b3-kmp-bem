package org.b3.bem.core.fact

import org.b3.bem.core.equipment.Equipment

data class MeasurementContext<E : Equipment>(
    val equipment: E,
) : Context
