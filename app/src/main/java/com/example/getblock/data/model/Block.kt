package com.example.getblock.data.model

import kotlinx.serialization.Serializable
import kotlinx.serialization.json.JsonIgnoreUnknownKeys

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
@JsonIgnoreUnknownKeys
data class BlockResult(
    val blockTime: Long,
    val blockhash: String,
    val previousBlockhash: String,
    val rewards: List<BlockReward>,
)

@Serializable
@JsonIgnoreUnknownKeys
data class BlockReward(
    val lamports: Long
)