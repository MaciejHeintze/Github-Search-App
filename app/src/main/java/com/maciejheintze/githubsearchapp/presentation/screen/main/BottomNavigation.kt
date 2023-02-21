package com.maciejheintze.githubsearchapp.presentation.screen.main

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.maciejheintze.githubsearchapp.NavigationItem
import com.maciejheintze.githubsearchapp.presentation.MainViewModel
import com.maciejheintze.githubsearchapp.presentation.ui.theme.Purple200

@Composable
fun BottomNavigation(
    navController: NavController,
    viewModel: MainViewModel,
) {
    val items = listOf(
        NavigationItem.Search,
        NavigationItem.History,
    )
    BottomNavigation(
        backgroundColor = Purple200,
        contentColor = Color.Black
    ) {
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        val currentRoute = navBackStackEntry?.destination?.route
        items.forEach { item ->
            BottomNavigationItem(
                icon = { Icon(painterResource(id = item.icon), contentDescription = item.title) },
                label = {
                    Text(
                        text = item.title,
                        fontSize = 16.sp
                    )
                },
                selectedContentColor = Color.Black,
                unselectedContentColor = Color.Black.copy(0.4f),
                alwaysShowLabel = true,
                selected = currentRoute == item.screenRoute,
                onClick = {
                    viewModel.getLocalRepositoriesDetailList()
                    navController.navigate(item.screenRoute) {
                        navController.graph.startDestinationRoute?.let { route ->
                            popUpTo(route) {
                                saveState = true
                            }
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }
}