package com.maciejheintze.githubsearchapp.data.remote.dto.commits

data class Verification(
    var payload: Any? = null,
    var reason: String = "",
    var signature: Any? = null,
    var verified: Boolean = false
)