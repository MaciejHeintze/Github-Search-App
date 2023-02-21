package com.maciejheintze.githubsearchapp.presentation

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.maciejheintze.githubsearchapp.base.BaseViewModel
import com.maciejheintze.githubsearchapp.db.model.CommitDetail
import com.maciejheintze.githubsearchapp.db.model.GithubRepositoryId
import com.maciejheintze.githubsearchapp.db.model.RepositoryEntity
import com.maciejheintze.githubsearchapp.domain.doToggleLoadingStateOf
import com.maciejheintze.githubsearchapp.domain.launchWith
import com.maciejheintze.githubsearchapp.domain.usecase.*
import kotlinx.coroutines.flow.*

class MainViewModel(
    private val getRepositoryUseCase: GetRepositoryUseCase,
    private val getCommitsUseCase: GetCommitsUseCase,
    private val saveRepositoryDetailUseCase: SaveRepositoryDetailUseCase,
    private val getLocalRepositoryDetailsListUseCase: GetLocalRepositoryDetailsListUseCase,
    private val getRepositoryDetailsUseCase: GetRepositoryDetailsUseCase,
) : BaseViewModel() {

    private val _repositoryId = MutableLiveData<GithubRepositoryId>()
    val repositoryId: LiveData<GithubRepositoryId>
        get() = _repositoryId

    private val _commits = MutableLiveData<List<CommitDetail>>()
    val commits: LiveData<List<CommitDetail>>
        get() = _commits

    private val _repositoryDetailList = MutableLiveData<List<RepositoryEntity>>()
    val repositoryDetailList: LiveData<List<RepositoryEntity>>
        get() = _repositoryDetailList

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

            saveRepositoryDetailsToLocalDb(
                owner = owner,
                repo = repo,
                id = id.id,
                commitsList = commitsList,
            )
        }
            .doToggleLoadingStateOf(this)
            .launchWith(
                scope = viewModelScope,
                onError = {
                    showErrorMessage(it)
                }
            )
    }

    private fun saveRepositoryDetailsToLocalDb(
        owner: String,
        repo: String,
        id: Int,
        commitsList: List<CommitDetail>,
    ) {
        val repositoryDetail = RepositoryEntity(
            id = id,
            owner = owner,
            repo = repo,
            commitDetails = commitsList,
        )
        saveRepositoryDetailUseCase(repositoryDetail)
            .doToggleLoadingStateOf(this)
            .launchWith(
                scope = viewModelScope,
                onError = {
                    showErrorMessage(it)
                }
            )
    }

    fun getLocalRepositoriesDetailList() {
        getLocalRepositoryDetailsListUseCase(Unit)
            .doToggleLoadingStateOf(this)
            .onEach {
                _repositoryDetailList.value = it
            }
            .doToggleLoadingStateOf(this)
            .launchWith(
                scope = viewModelScope,
                onError = {
                    showErrorMessage(it)
                }
            )
    }

    fun getRepositoryDetails(repositoryEntity: RepositoryEntity) {
        getRepositoryDetailsUseCase(repositoryEntity)
            .doToggleLoadingStateOf(this)
            .onEach {
                _repositoryId.value = GithubRepositoryId(id = it.id)
                _commits.value = it.commitDetails
            }
            .launchWith(
                scope = viewModelScope,
                onError = {
                    showErrorMessage(it)
                }
            )
    }
}