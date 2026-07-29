package org.b3.bem.sdk.model

data class Fact(
    val context: Context,
    val flows: List<Flow<*>>
)
