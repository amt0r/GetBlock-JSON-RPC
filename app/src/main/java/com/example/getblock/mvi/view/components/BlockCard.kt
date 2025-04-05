package com.example.getblock.mvi.view.components


import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.getblock.data.model.Block

@Composable
fun BlockCard(block: Block) {
    val fontSize = 16.sp

    Column(
        modifier = Modifier
            .background(
                Color.White,
                shape = androidx.compose.foundation.shape.RoundedCornerShape(8.dp)
            )
            .border(
                0.1.dp,
                Color.LightGray,
                shape = androidx.compose.foundation.shape.RoundedCornerShape(8.dp)
            )
            .padding(16.dp),
        verticalArrangement = Arrangement.spacedBy(20.dp)
    ) {
        Text(
            text = "Overview",
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            style = TextStyle(fontSize = fontSize)
        )
        Text(
            text = "Block",
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            style = TextStyle(fontSize = fontSize)
        )
        Text(
            text = "${block.block}",
            color = Color.Black,
            style = TextStyle(fontSize = fontSize)
        )
        Text(
            text = "Timestamp",
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            style = TextStyle(fontSize = fontSize)
        )
        Text(
            text = "${block.time}",
            color = Color.Black,
            style = TextStyle(fontSize = fontSize)
        )
        Text(
            text = "Blockhash",
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            style = TextStyle(fontSize = fontSize)
        )
        Text(
            text = "${block.blockhash}",
            color = Color.Black,
            style = TextStyle(fontSize = fontSize)
        )
        Text(
            text = "Epoch",
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            style = TextStyle(fontSize = fontSize)
        )
        Text(
            text = "${block.epoch}",
            color = Color.Black,
            style = TextStyle(fontSize = fontSize)
        )
        Text(
            text = "Reward",
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            style = TextStyle(fontSize = fontSize)
        )
        Text(
            text = "${block.reward}",
            color = Color.Black,
            style = TextStyle(fontSize = fontSize)
        )
        Text(
            text = "Previous Blockhash",
            fontWeight = FontWeight.Bold,
            color = Color.Black,
            style = TextStyle(fontSize = fontSize)
        )
        Text(
            text = "${block.previousBlockhash}",
            color = Color.Black,
            style = TextStyle(fontSize = fontSize)
        )
    }
}


@Preview(showBackground = true)
@Composable
fun PreviewBlockCard() {
    BlockCard(
        block = Block(
            block = 1234567890,
            blockhash = "0xabc1234567890defgh",
            previousBlockhash = "0xdef0987654321abcd",
            epoch = 5,
            time = "2025-04-05T12:00:00",
            reward = 12.34f
        )
    )
}