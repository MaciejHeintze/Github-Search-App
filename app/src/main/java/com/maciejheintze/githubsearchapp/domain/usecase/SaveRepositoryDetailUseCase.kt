package com.maciejheintze.githubsearchapp.domain.usecase

import com.maciejheintze.githubsearchapp.base.BaseUseCase
import com.maciejheintze.githubsearchapp.db.RepositoryDao
import com.maciejheintze.githubsearchapp.db.model.RepositoryEntity
import com.maciejheintze.githubsearchapp.domain.flowSingle
import kotlinx.coroutines.flow.Flow

class SaveRepositoryDetailUseCase(
    private val dao: RepositoryDao,
) : BaseUseCase<RepositoryEntity, Unit>() {
    override fun onBuild(params: RepositoryEntity): Flow<Unit> = flowSingle {
        dao.insert(params)
    }
}