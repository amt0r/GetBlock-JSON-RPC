package com.example.getblock.mvi.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.remember
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.getblock.domain.UseCase
import com.example.getblock.mvi.intent.MainIntent
import com.example.getblock.mvi.viewmodel.MainViewModel

@Composable
fun MainScreenWrapper() {
    val navController = rememberNavController()
    val useCase = remember { UseCase() }
    val viewModel: MainViewModel = viewModel { MainViewModel(useCase) }

    LaunchedEffect(Unit) {
        viewModel.dispatch(MainIntent.LoadData)
    }

    NavHost(navController = navController, startDestination = "main_screen") {
        composable("main_screen") {
            MainScreen(viewModel = viewModel, navController = navController)
        }
        composable("block_detail/{blockId}/{epoch}") { backStackEntry ->
            val blockId = backStackEntry.arguments?.getString("blockId")?.toLongOrNull() ?: 0L
            val epoch = backStackEntry.arguments?.getString("epoch")?.toIntOrNull() ?: 0

            BlockDetailScreen(blockId = blockId, epoch = epoch)
        }
    }
}