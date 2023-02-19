package com.maciejheintze.githubsearchapp.domain.usecase

import com.maciejheintze.githubsearchapp.base.BaseUseCase
import com.maciejheintze.githubsearchapp.data.remote.GithubApi
import com.maciejheintze.githubsearchapp.data.remote.dto.repositoryId.toGithubRepository
import com.maciejheintze.githubsearchapp.domain.flowSingle
import com.maciejheintze.githubsearchapp.db.model.GithubRepositoryId
import kotlinx.coroutines.flow.Flow

class GetRepositoryUseCase(
    private val api: GithubApi
) : BaseUseCase<RepositoryIdUseCaseParams, GithubRepositoryId>() {

    override fun onBuild(params: RepositoryIdUseCaseParams): Flow<GithubRepositoryId> = flowSingle {
        val query = "${params.owner}/${params.repo}"
        api.getGithubRepositoryByName(q = query).toGithubRepository()
    }
}

data class RepositoryIdUseCaseParams(
    val owner: String = "",
    val repo: String = "",
)