package com.maciejheintze.githubsearchapp.presentation.screen.main

import androidx.compose.animation.Crossfade
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import com.maciejheintze.githubsearchapp.NavigationItem
import com.maciejheintze.githubsearchapp.presentation.MainViewModel
import com.maciejheintze.githubsearchapp.presentation.screen.history.HistoryScreen
import com.maciejheintze.githubsearchapp.presentation.screen.search.SearchScreen

@Composable
fun MainScreen(viewModel: MainViewModel) {
    val items = listOf(
        NavigationItem.Search,
        NavigationItem.History
    )

    var selectedItem by remember { mutableStateOf<NavigationItem>(NavigationItem.Search) }

    Scaffold(
        bottomBar = {
            BottomNavigation {
                items.forEach { item ->
                    BottomNavigationItem(
                        icon = { Icon(item.icon, contentDescription = item.title) },
                        label = { Text(item.title) },
                        selected = selectedItem == item,
                        onClick = {
                            selectedItem = item
                            if(item == NavigationItem.History) {
                                viewModel.getLocalRepositoryDetailList()
                            }
                        }
                    )
                }
            }
        }
    ) { innerPadding ->
        Crossfade(selectedItem) { item ->
            when (item) {
                NavigationItem.Search -> SearchScreen(viewModel, Modifier.padding(innerPadding))
                NavigationItem.History -> HistoryScreen(viewModel, Modifier.padding(innerPadding))
            }
        }
    }
}