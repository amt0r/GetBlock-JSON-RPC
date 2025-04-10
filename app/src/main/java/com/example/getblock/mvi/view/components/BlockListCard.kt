package com.example.getblock.mvi.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.getblock.data.model.Block
import com.example.getblock.mvi.intent.MainIntent
import com.example.getblock.mvi.viewmodel.MainViewModel

@Composable
fun BlockListCard(blocks: List<Block>, viewModel: MainViewModel) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(
                color = Color.White,
                shape = RoundedCornerShape(8.dp)
            )
            .border(
                0.5.dp,
                Color.LightGray,
                shape = RoundedCornerShape(8.dp)
            )
            .padding(8.dp)
            .heightIn(max = 300.dp)
    ) {
        Text(
            text = "Blocks List",
            color = Color.DarkGray,
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(8.dp, 4.dp)
        )

        if (blocks.isNotEmpty()) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 8.dp)
                    .background(
                        color = Color(0xFFF5F5F5),
                        shape = RoundedCornerShape(8.dp)
                    )
            ) {
                LazyColumn(
                    modifier = Modifier
                        .fillMaxWidth()
                        .heightIn(max = 220.dp)
                ) {
                    itemsIndexed(blocks) { index, block ->
                        Column(
                            modifier = Modifier
                                .fillMaxWidth()
                                .clickable {
                                    viewModel.dispatch(MainIntent.GoToBlock(block.block))
                                }
                                .padding(12.dp)
                        ) {
                            Text(
                                text = "Block ID: ${block.block}",
                                color = Color(0xFF3491FF),
                                fontWeight = FontWeight.Medium
                            )

                            Text(
                                text = "Hash: ${block.blockhash.take(8)}...",
                                color = Color.DarkGray,
                                fontSize = 14.sp
                            )

                            Text(
                                text = "Created: ${block.time}",
                                color = Color(0xFF9E9E9E),
                                fontSize = 12.sp
                            )
                        }

                        if (index < blocks.size - 1) {
                            HorizontalDivider(
                                thickness = 1.dp,
                                color = Color(0xFFE0E0E0)
                            )
                        }
                    }
                }
            }
        } else {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp, vertical = 8.dp)
                    .background(
                        color = Color(0xFFF5F5F5),
                        shape = RoundedCornerShape(8.dp)
                    )
                    .padding(12.dp),
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center
            ) {
                Text(
                    text = "No blocks available",
                    color = Color(0xFF9E9E9E),
                    modifier = Modifier.padding(vertical = 16.dp)
                )
            }
        }
    }
}