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
    val popup = MutableLiveData<Alert.Popup>()

    fun showErrorMessage(throwable: Throwable) {
        toast.value = Alert.Toast(throwable.toString())
    }

    fun showPopup(
        title: String? = "Popup title",
        message: String? = "Popup message",
        buttonLabel: String? = "OK",
        onDismiss: (() -> Unit)? = null,
    ) {
        popup.value = Alert.Popup(
            title = title,
            message = message,
            buttonLabel = buttonLabel,
            onDismiss = onDismiss,
        )
    }

    override val coroutineContext: CoroutineContext
        get() = viewModelScope.coroutineContext
}