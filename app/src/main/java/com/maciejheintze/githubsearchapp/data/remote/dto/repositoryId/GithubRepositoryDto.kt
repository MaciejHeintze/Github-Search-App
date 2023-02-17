package com.maciejheintze.githubsearchapp.data.remote.dto.repositoryId

import com.google.gson.annotations.SerializedName
import com.maciejheintze.githubsearchapp.db.model.GithubRepositoryId

data class GithubRepositoryDto(
    @SerializedName("incomplete_results")
    var incompleteResults: Boolean = false,
    var items: List<Item> = listOf(),
    @SerializedName("total_count")
    var totalCount: Int = 0
)

fun GithubRepositoryDto.toGithubRepository(): GithubRepositoryId {
    return GithubRepositoryId(
        id = items.first().id
    )
}
