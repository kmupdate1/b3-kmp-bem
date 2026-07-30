package org.b3.bem.core.fact

data class Fact(
    val context: Context,
    val flows: List<Flow<*>>
)
