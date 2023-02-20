package com.maciejheintze.githubsearchapp.domain.usecase

import com.maciejheintze.githubsearchapp.base.BaseUseCase
import com.maciejheintze.githubsearchapp.db.RepositoryDao
import com.maciejheintze.githubsearchapp.db.model.RepositoryEntity
import com.maciejheintze.githubsearchapp.domain.flowSingle
import kotlinx.coroutines.flow.Flow

class GetLocalRepositoryDetailsListUseCase(
    private val dao: RepositoryDao,
) : BaseUseCase<Unit, List<RepositoryEntity>>() {
    override fun onBuild(params: Unit): Flow<List<RepositoryEntity>> = flowSingle {
        dao.getRepositoryHistory()
    }
}