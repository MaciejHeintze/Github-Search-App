package com.maciejheintze.githubsearchapp

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
import com.maciejheintze.githubsearchapp.base.BaseActivity
import com.maciejheintze.githubsearchapp.presentation.MainViewModel
import com.maciejheintze.githubsearchapp.presentation.screen.main.MainScreen
import com.maciejheintze.githubsearchapp.presentation.ui.theme.GithubSearchAppTheme
import org.koin.androidx.viewmodel.ext.android.viewModel

class MainActivity : BaseActivity() {

    private val viewModel: MainViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            GithubSearchAppTheme {
                MainScreen(viewModel = viewModel)
                SetLoading(viewModel = viewModel)
            }
        }
    }

}

sealed class NavigationItem(val title: String, val icon: Int, val screenRoute: String) {
    object Search : NavigationItem("Search", R.drawable.ic_search, "search")
    object History : NavigationItem("History", R.drawable.ic_history, "history")
}




