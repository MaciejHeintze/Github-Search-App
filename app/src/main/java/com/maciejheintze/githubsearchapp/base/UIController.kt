package com.maciejheintze.githubsearchapp.base

import androidx.compose.foundation.layout.BoxScope
import androidx.compose.runtime.Composable
import kotlinx.coroutines.CoroutineScope

interface UIController : CoroutineScope {

    @Composable
    fun setLoading(
        isLoading: Boolean,
        LoadingBackground: @Composable BoxScope.() -> Unit
    )
}