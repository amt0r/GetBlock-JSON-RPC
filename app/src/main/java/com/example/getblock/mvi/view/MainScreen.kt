package com.example.getblock.mvi.view

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.example.getblock.mvi.intent.MainIntent
import com.example.getblock.mvi.view.components.BlockListCard
import com.example.getblock.mvi.view.components.EpochCard
import com.example.getblock.mvi.view.components.SupplyCard
import com.example.getblock.mvi.view.components.TopPart
import com.example.getblock.mvi.viewmodel.MainViewModel

@Composable
fun MainScreen(viewModel: MainViewModel, navController: NavHostController) {
    val navigateToBlock by viewModel.navigateToBlock.collectAsState(initial = null)

    LaunchedEffect(navigateToBlock) {
        navigateToBlock?.let { (blockId, epoch) ->
            navController.navigate("block_detail/$blockId/$epoch")
        }
    }

    val state = viewModel.state.collectAsState()

    if (state.value.isLoading) {
        CircularProgressIndicator(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center),
            color = Color(0xFFB623CE),
            strokeWidth = 6.dp
        )
    } else {
        state.value.error?.let { errorMessage ->
            val context = LocalContext.current

            LaunchedEffect(errorMessage) {
                Toast.makeText(context, errorMessage, Toast.LENGTH_SHORT).show()
                viewModel.dispatch(MainIntent.ClearError)
            }
        }

        LazyColumn {
            item {
                TopPart(viewModel = viewModel)
            }

            item {
                Spacer(modifier = Modifier.height(16.dp))
            }

            item {
                Column(modifier = Modifier.padding(16.dp)) {
                    SupplyCard(supply = state.value.supply)

                    Spacer(modifier = Modifier.height(16.dp))

                    EpochCard(epoch = state.value.epoch)

                    Spacer(modifier = Modifier.height(16.dp))

                    BlockListCard(
                        blocks = state.value.blocks,
                        viewModel = viewModel
                    )

                    Spacer(modifier = Modifier.height(16.dp))
                }
            }
        }
    }
}