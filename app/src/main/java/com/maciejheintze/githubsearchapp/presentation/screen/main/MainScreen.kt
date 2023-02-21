package com.maciejheintze.githubsearchapp.presentation.screen.main

import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.navigation.compose.rememberNavController
import com.maciejheintze.githubsearchapp.presentation.MainViewModel

@Composable
fun MainScreen(viewModel: MainViewModel) {
    val navController = rememberNavController()
    Scaffold(
        bottomBar = {
            BottomNavigation(navController = navController, viewModel = viewModel)
        }
    ) {
        NavGraph(navController = navController, viewModel = viewModel)
    }
}