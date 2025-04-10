package com.example.getblock.data.model

import kotlinx.serialization.Serializable

data class Supply(
    val circulating: Long = 0,
    val nonCirculating: Long = 0,
    val total: Long = 0,
    val percentNonCirculating: Float = 0f,
    val percentCirculating: Float = 0f
)

@Serializable
data class SupplyValue(
    val circulating: Long,
    val nonCirculating: Long,
    val total: Long
)

@Serializable
data class SupplyResult(
    val value: SupplyValue
)
