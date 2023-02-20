package com.maciejheintze.githubsearchapp.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "repository")
data class RepositoryEntity(
    @PrimaryKey val id: Int,
    val owner: String,
    val repo: String,
    val commitDetails: List<CommitDetail>
)

