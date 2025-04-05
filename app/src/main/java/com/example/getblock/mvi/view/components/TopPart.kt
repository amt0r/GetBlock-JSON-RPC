package com.example.getblock.mvi.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.getblock.domain.UseCase
import com.example.getblock.mvi.viewmodel.MainViewModel

@Composable
fun TopPart(viewModel: MainViewModel) {
    Column(
        modifier = Modifier
            .background(
                brush = Brush.linearGradient(
                    colors = listOf(Color(0xFFB30EFC), Color(0xFF00D0C1))
                )
            )
            .fillMaxWidth()
            .heightIn(max = 300.dp)
    ) {
        Spacer(modifier = Modifier.height(35.dp))

        Text(
            text = "SOLSCAN",
            fontWeight = FontWeight.Bold,
            color = Color.White,
            style = TextStyle(fontSize = 25.sp),
            modifier = Modifier.padding(16.dp, 0.dp)
        )

        Spacer(modifier = Modifier.height(60.dp))

        Text(
            text = "Explore Solana Blockchain",
            fontWeight = FontWeight.Bold,
            color = Color.White,
            style = TextStyle(fontSize = 25.sp),
            modifier = Modifier.padding(16.dp, 0.dp)
        )

        Spacer(modifier = Modifier.height(16.dp))

        SearchBar(viewModel = viewModel)
    }
}


@Preview(showBackground = true)
@Composable
fun TopPartPreview() {
    TopPart(viewModel = MainViewModel(UseCase()))
}