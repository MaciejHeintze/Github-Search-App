package com.maciejheintze.githubsearchapp.domain.usecase

import com.maciejheintze.githubsearchapp.base.BaseUseCase
import com.maciejheintze.githubsearchapp.db.RepositoryDao
import com.maciejheintze.githubsearchapp.db.model.RepositoryEntity
import com.maciejheintze.githubsearchapp.domain.flowSingle
import kotlinx.coroutines.flow.Flow

class GetRepositoryDetailsUseCase(
    private val dao: RepositoryDao,
) : BaseUseCase<RepositoryEntity, RepositoryEntity>() {
    override fun onBuild(params: RepositoryEntity): Flow<RepositoryEntity> = flowSingle {
        dao.getRepositoryDetails(params.id)
    }
}