package com.maciejheintze.githubsearchapp.data.remote.dto.commits

import com.google.gson.annotations.SerializedName
import com.maciejheintze.githubsearchapp.db.model.CommitDetail

data class CommitsDtoItem(
    var author: Author? = null,
    @SerializedName("comments_url")
    var commentsUrl: String = "",
    var commit: Commit = Commit(),
    var committer: Any? = null,
    @SerializedName("html_url")
    var htmlUrl: String = "",
    @SerializedName("node_id")
    var nodeId: String = "",
    var parents: List<Any> = listOf(),
    var sha: String = "",
    var url: String = ""
)

fun CommitsDtoItem.toCommitDetail(): CommitDetail {
    return CommitDetail(
        message = commit.message,
        shaValue = sha,
        authorName = commit.author.name,
    )
}