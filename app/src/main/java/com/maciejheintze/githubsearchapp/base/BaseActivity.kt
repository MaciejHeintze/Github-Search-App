package com.maciejheintze.githubsearchapp.base

import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.CoroutineScope

open class BaseActivity : ComponentActivity(), UIController, LifecycleOwner, CoroutineScope {

    override val coroutineContext = lifecycleScope.coroutineContext

    @Composable
    override fun setLoading(
        isLoading: Boolean,
        LoadingBackground: @Composable BoxScope.() -> Unit,
    ) {
        if (isLoading) {
            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                CircularProgressIndicator(
                    modifier = Modifier.size(104.dp),
                    color = Color.Green
                )
            }
        }
    }

    @Composable
    fun SetLoading(
        viewModel: BaseViewModel,
        LoadingBackground: @Composable BoxScope.() -> Unit = {
            Box {}
        },
    ) {
        viewModel.isLoading.observeAsState().value?.let {
            setLoading(
                isLoading = it.isLoading,
                LoadingBackground = LoadingBackground
            )
        }
    }
}