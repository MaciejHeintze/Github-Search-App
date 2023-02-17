package com.maciejheintze.githubsearchapp.data.remote

import com.maciejheintze.githubsearchapp.data.remote.dto.GithubRepositoryDto
import com.maciejheintze.githubsearchapp.data.remote.dto.commits.CommitsDto
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface GithubApi {

    @GET(REPOSITORY)
    suspend fun getGithubRepositoryByName(@Query("q") q: String): GithubRepositoryDto

    @GET(COMMITS)
    suspend fun getCommits(@Path("owner") owner: String, @Path("repo") repo: String): CommitsDto
}