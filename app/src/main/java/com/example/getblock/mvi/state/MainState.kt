package com.example.getblock.mvi.state

import com.example.getblock.data.model.Block
import com.example.getblock.data.model.Epoch
import com.example.getblock.data.model.Supply

data class MainState(
    val isLoading: Boolean = false,
    val epoch: Epoch = Epoch(),
    val supply: Supply = Supply(),
    val blocks: List<Block> = emptyList(),
    val error: String? = null
)