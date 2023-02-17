package com.maciejheintze.githubsearchapp.domain.model

data class CommitDetail(
    val message: String,
    val shaValue: String,
    val authorName: String,
)
