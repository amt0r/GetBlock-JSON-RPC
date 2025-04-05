package com.example.getblock.mvi.reducer

import com.example.getblock.data.model.Block
import com.example.getblock.mvi.intent.BlockIntent
import com.example.getblock.mvi.state.BlockState

fun blockReducer(state: BlockState, intent: BlockIntent): BlockState {
    return when (intent) {
        is BlockIntent.LoadBlockInfo -> state.copy(isLoading = true, error = null)
        is BlockIntent.BlockLoadedSuccess -> state.copy(
            block = intent.block,
            isLoading = false,
            error = null
        )

        is BlockIntent.BlockLoadedError -> state.copy(error = intent.message, isLoading = false)
        is BlockIntent.GoBackToMain -> state.copy(block = Block(), isLoading = false, error = null)
    }
}
