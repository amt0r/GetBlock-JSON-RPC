package com.example.getblock.data.model

import kotlinx.serialization.Serializable

data class BlockList(
    val blocks: List<Block>
)

data class Block(
    val block: Long = 0,
    val time: String = "",
    val blockhash: String = "",
    val previousBlockhash: String = "",
    val epoch: Int = 0,
    val reward: Float = 0f
)

@Serializable
data class BlockResult(
    val blockTime: Long,
    val blockhash: String,
    val previousBlockhash: String,
    val rewards: List<BlockReward>,
)

@Serializable
data class BlockReward(
    val lamports: Long
)