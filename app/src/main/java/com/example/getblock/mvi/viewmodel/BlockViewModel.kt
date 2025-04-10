package com.example.getblock.mvi.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.getblock.domain.UseCase
import com.example.getblock.mvi.intent.BlockIntent
import com.example.getblock.mvi.reducer.blockReducer
import com.example.getblock.mvi.state.BlockState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class BlockViewModel(private val useCase: UseCase) : ViewModel() {

    private val _state = MutableStateFlow(BlockState())
    val state: StateFlow<BlockState> = _state

    fun dispatch(intent: BlockIntent) {
        _state.value = blockReducer(_state.value, intent)
        when (intent) {
            is BlockIntent.LoadBlockInfo -> loadBlockInfo(intent.blockId, intent.epoch)
            else -> Unit
        }
    }

    private fun loadBlockInfo(blockId: Long, epoch: Int) {
        viewModelScope.launch {
            try {
                val block = withContext(Dispatchers.IO) {
                    useCase.getBlockInfo(blockId, epoch)
                }

                if (block != null) {
                    dispatch(BlockIntent.BlockLoadedSuccess(block))
                } else {
                    dispatch(BlockIntent.BlockLoadedError("Не вдалося завантажити блок"))
                }
            } catch (e: Exception) {
                dispatch(BlockIntent.BlockLoadedError(e.message))
            }
        }
    }
}
