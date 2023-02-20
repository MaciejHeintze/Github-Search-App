package com.maciejheintze.githubsearchapp.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.maciejheintze.githubsearchapp.db.model.RepositoryEntity
import com.maciejheintze.githubsearchapp.db.util.Converters

@Database(
    entities = [RepositoryEntity::class],
    version = DATABASE_VERSION
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun getRepositoryDao(): RepositoryDao
}