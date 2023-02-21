package com.maciejheintze.githubsearchapp.base

sealed class Alert {

    data class Toast(
        val message: String,
    ) : Alert()

    data class Popup(
        val title: String? = "Popup title",
        val message: String? = "Popup message",
        val buttonLabel: String? = "OK",
        val onDismiss: (() -> Unit)? = null,
    ) : Alert()
}
