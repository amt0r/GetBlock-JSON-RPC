package com.example.getblock.domain

import android.text.format.DateUtils
import com.example.getblock.data.model.BlockResult
import com.example.getblock.data.model.EpochResult
import com.example.getblock.data.model.SupplyValue
import kotlin.math.round

object Utils {
    fun calculateSupplyPercentage(value: SupplyValue): Pair<Float, Float> {
        val percentCirculating = (value.circulating.toFloat() / value.total.toFloat()) * 100
        val percentNonCirculating = (value.nonCirculating.toFloat() / value.total.toFloat()) * 100
        return Pair(round(percentCirculating), round(percentNonCirculating))
    }

    fun calculateStartAndEndSlot(epochResult: EpochResult): Pair<Long, Long> {
        val startSlot = epochResult.absoluteSlot - epochResult.slotIndex
        val endSlot = startSlot + epochResult.slotsInEpoch - 1
        return Pair(startSlot, endSlot)
    }

    fun formatEpochTime(epochResult: EpochResult): String {
        val slotDurationInMillis = 400L
        val slotsRemaining = epochResult.slotsInEpoch - epochResult.slotIndex

        val remainingTimeInMillis = slotsRemaining * slotDurationInMillis

        val days = remainingTimeInMillis / (1000 * 60 * 60 * 24)
        val hours = (remainingTimeInMillis % (1000 * 60 * 60 * 24)) / (1000 * 60 * 60)
        val minutes = (remainingTimeInMillis % (1000 * 60 * 60)) / (1000 * 60)
        val seconds = (remainingTimeInMillis % (1000 * 60)) / 1000

        return buildString {
            if (days > 0) append("${days}d ")
            if (hours > 0) append("${hours}h ")
            if (minutes > 0) append("${minutes}m ")
            if (seconds > 0 || (days == 0L && hours == 0L && minutes == 0L)) append("${seconds}s")
        }.trim()
    }

    fun calculatePercentInEpoch(epochResult: EpochResult): Float {
        return (epochResult.slotIndex.toFloat() / epochResult.slotsInEpoch.toFloat()) * 100
    }

    fun calculateElapsedTime(block: BlockResult): String {
        return DateUtils.getRelativeTimeSpanString(
            block.blockTime * 1000,
            System.currentTimeMillis(),
            DateUtils.SECOND_IN_MILLIS
        ).toString()
    }

    fun calculateTotalReward(block: BlockResult): Float {
        return block.rewards.sumOf { it.lamports } / 1_000_000_000.0F
    }
}
