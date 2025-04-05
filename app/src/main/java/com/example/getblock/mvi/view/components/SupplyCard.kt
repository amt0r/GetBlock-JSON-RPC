package com.example.getblock.mvi.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import com.example.getblock.data.model.Supply

@Composable
fun SupplyCard(supply: Supply) {
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
            text = "SOL Supply",
            color = Color.DarkGray,
            modifier = Modifier.padding(8.dp, 4.dp)
        )

        Text(
            text = "${supply.total}",
            fontWeight = FontWeight.Bold,
            modifier = Modifier.padding(8.dp, 4.dp)
        )

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 8.dp)
                .background(
                    color = Color(0xFFF5F5F5),
                    shape = RoundedCornerShape(8.dp)
                )
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            ) {
                Text(
                    text = "Circulating Supply",
                    color = Color(0xFF9E9E9E),
                    modifier = Modifier.padding(4.dp)
                )
                Text(
                    text = "${supply.circulating} SOL (${supply.percentCirculating}%)",
                    color = Color.DarkGray,
                    modifier = Modifier.padding(4.dp)
                )

                HorizontalDivider(
                    modifier = Modifier.padding(8.dp),
                    thickness = 1.dp,
                    color = Color(0xFFE0E0E0)
                )

                Text(
                    text = "Non-circulating Supply",
                    color = Color(0xFF9E9E9E),
                    modifier = Modifier.padding(4.dp)
                )
                Text(
                    text = "${supply.nonCirculating} SOL (${supply.percentNonCirculating}%)",
                    color = Color.DarkGray,
                    modifier = Modifier.padding(4.dp)
                )
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun SupplyCardPreview() {
    SupplyCard(supply = Supply(5000, 1000, 6000, 17f, 83f))
}