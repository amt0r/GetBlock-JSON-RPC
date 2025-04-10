package com.example.getblock.data.model

import kotlinx.serialization.Serializable

data class Epoch(
    val epoch: Int = 0,
    val slotStart: Long = 0,
    val slotEnd: Long = 0,
    val absoluteSlot: Long = 0,
    val time: String = "",
    val percentInEpoch: Float = 0f
)

@Serializable
data class EpochResult(
    val absoluteSlot: Long,
    val epoch: Int,
    val slotIndex: Long,
    val slotsInEpoch: Long
)
