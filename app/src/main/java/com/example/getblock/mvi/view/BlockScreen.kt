package com.example.getblock.mvi.view

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.getblock.domain.UseCase
import com.example.getblock.mvi.intent.BlockIntent
import com.example.getblock.mvi.view.components.BlockCard
import com.example.getblock.mvi.viewmodel.BlockViewModel

@Composable
fun BlockDetailScreen(blockId: Long, epoch: Int) {
    val viewModel: BlockViewModel = viewModel { BlockViewModel(UseCase()) }
    LaunchedEffect(blockId, epoch) {
        viewModel.dispatch(
            BlockIntent.LoadBlockInfo(
                blockId,
                epoch
            )
        )
    }

    val state = viewModel.state.collectAsState().value

    if (state.isLoading) {
        CircularProgressIndicator(
            modifier = Modifier
                .fillMaxSize()
                .wrapContentSize(Alignment.Center)
                .background(Color.Transparent),
            color = Color(0xFFB623CE),
            strokeWidth = 6.dp
        )
    } else {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(20.dp)
        ) {
            state.error?.let {
                Toast.makeText(LocalContext.current, it, Toast.LENGTH_SHORT).show()
            }

            Spacer(modifier = Modifier.height(5.dp))

            Text(
                text = "Block Details",
                fontWeight = FontWeight.Bold,
                color = Color.Black,
                style = TextStyle(fontSize = 30.sp)
            )

            Text(
                text = "${blockId}",
                color = Color.Black,
                style = TextStyle(fontSize = 16.sp)
            )

            state.block?.let { block ->
                BlockCard(block = block)
            }
        }
    }
}