package com.example.getblock.domain

import com.example.getblock.data.api.ApiService
import com.example.getblock.data.model.Block
import com.example.getblock.data.model.BlockList
import com.example.getblock.data.model.Epoch
import com.example.getblock.data.model.Supply

const val NUM_OF_BLOCKS_IN_LIST = 4 // each block request takes a long time
const val DIVIDE = 1000000000L


class UseCase {
    suspend fun fetchData(): Triple<Supply?, Epoch?, BlockList?> {
        val supply = getSupplyInfo()
        val epoch = getEpochInfo()
        val blocks = epoch?.let { getBlockListData(it.absoluteSlot, epoch.epoch) }

        return Triple(supply, epoch, blocks)
    }


    private suspend fun getSupplyInfo(): Supply? {
        val response = ApiService.getSupply()

        return response?.result?.value?.let {
            val (percentCirculating, percentNonCirculating) = Utils.calculateSupplyPercentage(it)

            Supply(
                circulating = it.circulating / DIVIDE,
                nonCirculating = it.nonCirculating / DIVIDE,
                total = it.total / DIVIDE,
                percentCirculating = percentCirculating,
                percentNonCirculating = percentNonCirculating
            )
        }
    }

    private suspend fun getEpochInfo(): Epoch? {
        val response = ApiService.getEpoch()

        return response?.result?.let {
            val (startSlot, endSlot) = Utils.calculateStartAndEndSlot(it)
            val time = Utils.formatEpochTime(it)
            val percentInEpoch = Utils.calculatePercentInEpoch(it)

            Epoch(
                epoch = it.epoch,
                slotStart = startSlot,
                slotEnd = endSlot,
                time = time,
                percentInEpoch = percentInEpoch,
                absoluteSlot = it.absoluteSlot
            )
        }
    }

    suspend fun getBlockInfo(slot: Long, epoch: Int): Block? {
        val response = ApiService.getBlock(slot)

        return response?.result?.let {
            val time = Utils.calculateElapsedTime(it)
            val reward = Utils.calculateTotalReward(it)

            Block(
                block = slot,
                time = time,
                blockhash = it.blockhash,
                previousBlockhash = it.previousBlockhash,
                epoch = epoch,
                reward = reward
            )
        }
    }

    private suspend fun getBlockList(slot: Long): List<Long>? {
        val response = ApiService.getBlocks(slot - NUM_OF_BLOCKS_IN_LIST + 1, slot)
        return response?.result
    }

    private suspend fun getBlockListData(slot: Long, epoch: Int): BlockList? {
        val blocks = mutableListOf<Block>()

        val blockSlots = getBlockList(slot)

        blockSlots?.forEach { blockSlot ->
            val blockInfo = getBlockInfo(blockSlot, epoch)
            blockInfo?.let { blocks.add(it) }
        }

        return if (blocks.isNotEmpty()) {
            BlockList(blocks)
        } else {
            null
        }
    }

}