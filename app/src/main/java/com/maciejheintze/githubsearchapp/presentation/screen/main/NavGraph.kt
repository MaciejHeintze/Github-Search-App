package com.maciejheintze.githubsearchapp.presentation.screen.main

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.maciejheintze.githubsearchapp.NavigationItem
import com.maciejheintze.githubsearchapp.presentation.MainViewModel
import com.maciejheintze.githubsearchapp.presentation.screen.history.HistoryScreen
import com.maciejheintze.githubsearchapp.presentation.screen.search.SearchScreen

@Composable
fun NavGraph(
    navController: NavHostController,
    viewModel: MainViewModel,
) {
    NavHost(
        navController,
        startDestination = NavigationItem.Search.screenRoute
    ) {
        composable(NavigationItem.Search.screenRoute) {
            SearchScreen(viewModel = viewModel)
        }
        composable(NavigationItem.History.screenRoute) {
            HistoryScreen(viewModel = viewModel, navController = navController)
        }
    }
}