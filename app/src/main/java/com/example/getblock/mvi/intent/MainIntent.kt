package com.example.getblock.mvi.intent

import com.example.getblock.data.model.Block
import com.example.getblock.data.model.Epoch
import com.example.getblock.data.model.Supply

sealed class MainIntent {
    object LoadData : MainIntent()
    data class DataLoaded(val supply: Supply, val epoch: Epoch, val blocks: List<Block>) :
        MainIntent()

    data class SearchBlock(val blockId: Long) : MainIntent()
    data class GoToBlock(val blockId: Long) : MainIntent()
    object ClearError : MainIntent()
    data class SetError(val errorMessage: String?) : MainIntent()
}
