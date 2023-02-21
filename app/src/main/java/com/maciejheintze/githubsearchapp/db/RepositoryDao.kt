package com.maciejheintze.githubsearchapp.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.maciejheintze.githubsearchapp.db.model.RepositoryEntity

@Dao
interface RepositoryDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
     fun insert(repository: RepositoryEntity)

    @Query("SELECT * FROM repository")
     fun getRepositoryHistory(): List<RepositoryEntity>

    @Query("SELECT * FROM repository WHERE id = :id")
    fun getRepositoryDetails(id: Int): RepositoryEntity
}
