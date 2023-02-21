package com.maciejheintze.githubsearchapp.base

import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.lifecycleScope
import com.maciejheintze.githubsearchapp.presentation.ui.theme.Purple500
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
                    color = Purple500
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

    @Composable
    fun Toast(viewModel: BaseViewModel) {
        viewModel.toast.observeAsState().value?.let {
            showToast(it.message)
        }
    }

    override fun showToast(message: String) {
        Toast.makeText(applicationContext, message, Toast.LENGTH_LONG).show()
    }

    @Composable
    fun Popup(viewModel: BaseViewModel) {
        viewModel.popup.observeAsState().value?.let {
            showPopup(
                title = it.title,
                message = it.message,
                buttonLabel = it.buttonLabel,
                onDismiss = it.onDismiss,
            )
        }
    }

    @Composable
    override fun showPopup(
        title: String?,
        message: String?,
        buttonLabel: String?,
        onDismiss: (() -> Unit)?,
    ) {
        val openDialog = remember { mutableStateOf(true) }
        if (openDialog.value) {
            AlertDialog(
                onDismissRequest = { openDialog.value = false },
                title = { Text(title ?: "") },
                text = { Text(message ?: "") },
                confirmButton = {
                    Button(
                        onClick = {
                            openDialog.value = false
                            onDismiss?.invoke()
                        }) {
                        Text(buttonLabel ?: "")
                    }
                }
            )
        }
    }
}