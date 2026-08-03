package org.b3.bem.server.store.config

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
@SerialName("store")
data class StoreConfig(
    val database: String,
)
