package org.b3.bem.core.fact

data class CompositeFact(
    val context: Context,
    val facts: List<Fact>,
) : Fact
