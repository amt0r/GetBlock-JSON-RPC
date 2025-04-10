package com.example.getblock.mvi.reducer

import com.example.getblock.mvi.intent.MainIntent
import com.example.getblock.mvi.state.MainState

fun mainReducer(state: MainState, intent: MainIntent): MainState {
    return when (intent) {
        is MainIntent.LoadData -> {
            state.copy(isLoading = true, error = null)
        }

        is MainIntent.DataLoaded -> state.copy(
            isLoading = false,
            supply = intent.supply,
            epoch = intent.epoch,
            blocks = intent.blocks
        )

        is MainIntent.SearchBlock -> {
            state.copy(error = null)
        }

        is MainIntent.GoToBlock -> {
            state.copy(isLoading = false, error = null)
        }

        is MainIntent.ClearError -> {
            state.copy(error = null)
        }

        is MainIntent.SetError -> {
            state.copy(isLoading = false, error = intent.errorMessage)
        }
    }
}