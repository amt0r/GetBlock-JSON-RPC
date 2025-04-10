package com.example.getblock.mvi.state

import com.example.getblock.data.model.Block

data class BlockState(
    val block: Block = Block(),
    val isLoading: Boolean = false,
    val error: String? = null
)
