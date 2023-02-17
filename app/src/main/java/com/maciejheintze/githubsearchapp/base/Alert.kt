package com.maciejheintze.githubsearchapp.base

sealed class Alert {

    data class Toast(
        val message: String,
    ) : Alert()
}
