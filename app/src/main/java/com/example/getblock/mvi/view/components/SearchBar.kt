package com.example.getblock.mvi.view.components

import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.platform.LocalContext
import com.example.getblock.mvi.viewmodel.MainViewModel
import com.example.getblock.mvi.intent.MainIntent

@Composable
fun SearchBar(viewModel: MainViewModel) {
    var searchQuery by remember { mutableStateOf("") }
    val context = LocalContext.current

    OutlinedTextField(
        value = searchQuery,
        onValueChange = { searchQuery = it },
        label = { Text("Enter Block ID") },
        singleLine = true,
        modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp, 0.dp, 16.dp, 16.dp),
        shape = RoundedCornerShape(8.dp),
        colors = OutlinedTextFieldDefaults.colors(
            focusedTextColor = Color.Black,
            unfocusedTextColor = Color.Black,
            focusedBorderColor = Color.Gray,
            unfocusedBorderColor = Color.LightGray,
            cursorColor = Color.Black,
            focusedLabelColor = Color.Gray,
            unfocusedLabelColor = Color.Gray,
            unfocusedContainerColor = Color.White,
            focusedContainerColor = Color.White
        ),
        trailingIcon = {
            IconButton(
                onClick = {
                    val blockId = searchQuery.toLongOrNull()
                    if (blockId != null && blockId > 0) {
                        viewModel.dispatch(MainIntent.SearchBlock(blockId))
                    } else {
                        Toast.makeText(context, "Invalid Block ID", Toast.LENGTH_SHORT).show()
                    }
                },
                modifier = Modifier
                    .background(Color(0xFFB623CE), shape = RoundedCornerShape(8.dp))
                    .size(36.dp)
                    .padding(8.dp)
            ) {
                Icon(
                    imageVector = Icons.Filled.Search,
                    contentDescription = "Search",
                    tint = Color.White
                )
            }
        }
    )
}
