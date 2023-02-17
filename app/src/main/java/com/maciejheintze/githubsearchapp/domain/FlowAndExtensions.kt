package com.maciejheintze.githubsearchapp.domain

import com.maciejheintze.githubsearchapp.base.BaseViewModel
import com.maciejheintze.githubsearchapp.presentation.util.LoadingState
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*
import kotlin.coroutines.Continuation
import kotlin.coroutines.resumeWithException

fun <T> flowSingle(action: suspend () -> T): Flow<T> = flow { emit(action.invoke()) }

fun <T> Flow<T>.doToggleLoadingStateOf(
    vm: BaseViewModel,
): Flow<T> = this
    .onStart { vm.isLoading.value = LoadingState(isLoading = true) }
    .onCompletion { vm.isLoading.value = LoadingState(isLoading = false) }

fun <T> Flow<T>.launchWith(
    scope: CoroutineScope,
    continuation: Continuation<Unit>? = null,
    onError: ((Throwable) -> Unit),
) = this
    .catch {
        continuation?.resumeWithException(it)
        onError.invoke(it)
    }
    .launchIn(scope)