package org.b3.bem.core.model

import kotlin.uuid.Uuid

@JvmInline
value class ResourceId
private constructor(private val value: Uuid)
