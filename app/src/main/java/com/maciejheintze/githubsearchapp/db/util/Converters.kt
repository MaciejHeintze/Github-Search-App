package com.maciejheintze.githubsearchapp.db.util

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.maciejheintze.githubsearchapp.db.model.CommitDetail

@ProvidedTypeConverter
class Converters {
    private val gson = Gson()

    @TypeConverter
    fun fromCommitDetailsList(commitDetails: List<CommitDetail>): String {
        return gson.toJson(commitDetails)
    }

    @TypeConverter
    fun toCommitDetailsList(commitDetailsJson: String): List<CommitDetail> {
        val type = object : TypeToken<List<CommitDetail>>() {}.type
        return gson.fromJson(commitDetailsJson, type)
    }

}