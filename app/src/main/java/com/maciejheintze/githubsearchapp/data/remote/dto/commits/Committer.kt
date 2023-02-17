package com.maciejheintze.githubsearchapp.data.remote.dto.commits

data class Committer(
    var date: String = "",
    var email: String = "",
    var name: String = ""
)