package org.b3.bem.core.model

data class Fact(
    val context: Context,
    val flows: List<Flow<*>>
)
