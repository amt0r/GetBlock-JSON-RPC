package com.example.getblock.mvi.view.components

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.getblock.data.model.Epoch

@Composable
fun EpochCard(epoch: Epoch) {
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
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp, 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "Current Epoch",
                color = Color.DarkGray
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                Box(
                    modifier = Modifier
                        .width(80.dp)
                        .height(4.dp)
                        .background(Color.LightGray, RoundedCornerShape(2.dp))
                ) {
                    Box(
                        modifier = Modifier
                            .width(80.dp * epoch.percentInEpoch / 100)
                            .height(4.dp)
                            .background(Color(0xFF00E096), RoundedCornerShape(2.dp))
                    )
                }
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp, 4.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = "${epoch.epoch}",
                fontWeight = FontWeight.Bold,
                color = Color(0xFF3491FF),
                fontSize = 24.sp
            )

            Text(
                text = "(${epoch.percentInEpoch}%)",
                color = Color.Gray
            )
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 8.dp, vertical = 8.dp)
                .background(
                    color = Color(0xFFF5F5F5),
                    shape = RoundedCornerShape(8.dp)
                )
                .padding(12.dp)
        ) {
            Text(
                text = "Slot Range",
                color = Color(0xFF9E9E9E),
                modifier = Modifier.padding(vertical = 4.dp)
            )

            Text(
                text = "${epoch.slotStart} to ${epoch.slotEnd}",
                color = Color.DarkGray,
                modifier = Modifier.padding(vertical = 4.dp)
            )

            HorizontalDivider(
                modifier = Modifier.padding(vertical = 8.dp),
                thickness = 1.dp,
                color = Color(0xFFE0E0E0)
            )

            Text(
                text = "Time Remain",
                color = Color(0xFF9E9E9E),
                modifier = Modifier.padding(vertical = 4.dp)
            )

            Text(
                text = "${epoch.time}",
                color = Color.DarkGray,
                modifier = Modifier.padding(vertical = 4.dp)
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun EpochCardPreview() {
    EpochCard(epoch = Epoch(1, 100, 200, 300, "2025-04-05", 50f))
}
