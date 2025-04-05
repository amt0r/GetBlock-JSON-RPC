package com.example.getblock.mvi.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.getblock.data.model.Epoch
import com.example.getblock.data.model.Supply
import com.example.getblock.domain.UseCase
import com.example.getblock.mvi.intent.MainIntent
import com.example.getblock.mvi.state.MainState
import com.example.getblock.mvi.reducer.mainReducer
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class MainViewModel(
    private val useCase: UseCase
) : ViewModel() {
    private val _state = MutableStateFlow(MainState())
    val state: StateFlow<MainState> = _state

    private val _navigateToBlock = MutableSharedFlow<Pair<Long, Int>>(replay = 0)
    val navigateToBlock: SharedFlow<Pair<Long, Int>> = _navigateToBlock

    init {
        startDataRefresh()
    }

    fun dispatch(intent: MainIntent) {
        _state.value = mainReducer(_state.value, intent)
        when (intent) {
            is MainIntent.LoadData -> loadData()
            is MainIntent.DataLoaded -> {}
            is MainIntent.GoToBlock -> onBlockFound(intent.blockId, _state.value.epoch.epoch)
            is MainIntent.SearchBlock -> searchBlock(intent.blockId)
            is MainIntent.GoToBlock -> {}
            is MainIntent.ClearError -> {}
            is MainIntent.SetError -> {}
        }
    }

    private fun startDataRefresh() {
        viewModelScope.launch {
            while (true) {
                dispatch(MainIntent.LoadData)
                delay(60_000)
            }
        }
    }

    private fun loadData() {
        viewModelScope.launch {
            try {
                val (supply, epoch, blockList) = useCase.fetchData()

                val blocks = blockList?.blocks ?: emptyList()

                dispatch(
                    MainIntent.DataLoaded(
                        supply ?: Supply(),
                        epoch ?: Epoch(),
                        blocks
                    )
                )
            } catch (e: Exception) {
                e.message?.let { handleError(it) }
            }
        }
    }

    private fun searchBlock(blockId: Long) {
        if (blockId <= 0) {
            handleError("Invalid ID")
            return
        }

        viewModelScope.launch {
            try {
                val block = useCase.getBlockInfo(blockId, _state.value.epoch.epoch)
                if (block != null) {
                    onBlockFound(blockId, _state.value.epoch.epoch)
                } else {
                    handleError("Block not found")
                }
            } catch (e: Exception) {
                handleError("Block loading error")
            }
        }
    }

    private fun handleError(message: String) {
        dispatch(MainIntent.SetError(message))
    }

    private fun onBlockFound(blockId: Long, epoch: Int) {
        viewModelScope.launch {
            _navigateToBlock.emit(Pair(blockId, epoch))
        }
    }
}