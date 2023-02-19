package com.maciejheintze.githubsearchapp.domain.usecase

import com.maciejheintze.githubsearchapp.base.BaseUseCase
import com.maciejheintze.githubsearchapp.data.remote.GithubApi
import com.maciejheintze.githubsearchapp.data.remote.dto.commits.toCommitDetail
import com.maciejheintze.githubsearchapp.db.model.CommitDetail
import com.maciejheintze.githubsearchapp.domain.flowSingle
import kotlinx.coroutines.flow.Flow

class GetCommitsUseCase(
    private val api: GithubApi
) : BaseUseCase<CommitDetailUseCaseParams, List<CommitDetail>>() {

    override fun onBuild(params: CommitDetailUseCaseParams): Flow<List<CommitDetail>> = flowSingle {
        api.getCommits(
            owner = params.owner,
            repo = params.repo,
        ).map {
            it.toCommitDetail()
        }
    }
}

data class CommitDetailUseCaseParams(
    val owner: String = "",
    val repo: String = "",
)
