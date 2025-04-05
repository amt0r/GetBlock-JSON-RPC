package com.example.getblock.mvi.intent

import com.example.getblock.data.model.Block

sealed class BlockIntent {
    data class LoadBlockInfo(val blockId: Long, val epoch: Int) : BlockIntent()
    data class BlockLoadedSuccess(val block: Block) : BlockIntent()
    data class BlockLoadedError(val message: String?) : BlockIntent()
    object GoBackToMain : BlockIntent()
}

