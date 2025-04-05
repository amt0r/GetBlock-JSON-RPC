package com.example.getblock.data.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonIgnoreUnknownKeys

data class Supply(
    val circulating: Long = 0,
    val nonCirculating: Long = 0,
    val total: Long = 0,
    val percentNonCirculating: Float = 0f,
    val percentCirculating: Float = 0f
)

@Serializable
@JsonIgnoreUnknownKeys
data class SupplyValue(
    val circulating: Long,
    val nonCirculating: Long,
    val total: Long
)

@Serializable
@JsonIgnoreUnknownKeys
data class SupplyResult(
    val value: SupplyValue
)
