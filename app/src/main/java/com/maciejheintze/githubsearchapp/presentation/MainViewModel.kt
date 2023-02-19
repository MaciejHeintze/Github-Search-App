package com.maciejheintze.githubsearchapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.maciejheintze.githubsearchapp.base.BaseViewModel
import com.maciejheintze.githubsearchapp.db.model.CommitDetail
import com.maciejheintze.githubsearchapp.db.model.GithubRepositoryId
import com.maciejheintze.githubsearchapp.domain.doToggleLoadingStateOf
import com.maciejheintze.githubsearchapp.domain.launchWith
import com.maciejheintze.githubsearchapp.domain.usecase.CommitDetailUseCaseParams
import com.maciejheintze.githubsearchapp.domain.usecase.GetCommitsUseCase
import com.maciejheintze.githubsearchapp.domain.usecase.GetRepositoryUseCase
import com.maciejheintze.githubsearchapp.domain.usecase.RepositoryIdUseCaseParams
import kotlinx.coroutines.flow.*

class MainViewModel(
    private val getRepositoryUseCase: GetRepositoryUseCase,
    private val getCommitsUseCase: GetCommitsUseCase
) : BaseViewModel() {

    private val _repositoryId = MutableLiveData<GithubRepositoryId>()
    val repositoryId: LiveData<GithubRepositoryId>
        get() = _repositoryId

    private val _commits = MutableLiveData<List<CommitDetail>>()
    val commits: LiveData<List<CommitDetail>>
        get() = _commits

    fun fetchRepositoryIdAndCommits(owner: String, repo: String) {
        getRepositoryUseCase(
            params = RepositoryIdUseCaseParams(
                owner = owner,
                repo = repo,
            )
        ).zip(
            getCommitsUseCase(
                params = CommitDetailUseCaseParams(
                    owner = owner,
                    repo = repo,
                )
            )
        ) { id, commitsList ->
            _repositoryId.value = id
            _commits.value = commitsList
        }
            .doToggleLoadingStateOf(this)
            .launchWith(
                scope = viewModelScope,
                onError = {
                    showErrorMessage(it)
                }
            )
    }

}