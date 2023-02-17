package com.maciejheintze.githubsearchapp.base

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.maciejheintze.githubsearchapp.presentation.util.LoadingState
import kotlinx.coroutines.CoroutineScope
import kotlin.coroutines.CoroutineContext

open class BaseViewModel : ViewModel(), CoroutineScope {

    val isLoading = MutableLiveData<LoadingState>()
    val toast = MutableLiveData<Alert.Toast>()

    fun showErrorMessage(throwable: Throwable) {
        toast.value = Alert.Toast(throwable.toString())
    }

    override val coroutineContext: CoroutineContext
        get() = viewModelScope.coroutineContext
}