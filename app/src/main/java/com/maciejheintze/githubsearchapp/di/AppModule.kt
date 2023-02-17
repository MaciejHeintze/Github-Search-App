package com.maciejheintze.githubsearchapp.di

import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.maciejheintze.githubsearchapp.data.providers.RetrofitProviderImpl
import com.maciejheintze.githubsearchapp.data.remote.GithubApi
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit

val appModule = module {

    single<Gson> { GsonBuilder().create() }

    single<OkHttpClient> {
        val logging = HttpLoggingInterceptor()
        logging.setLevel(HttpLoggingInterceptor.Level.BASIC)

        OkHttpClient.Builder()
            .connectTimeout(10, TimeUnit.SECONDS)
            .writeTimeout(10, TimeUnit.SECONDS)
            .readTimeout(10, TimeUnit.SECONDS)
            .addInterceptor(logging)
            .build()
    }
    single { RetrofitProviderImpl(okHttpClient = get(), gson = get()).provideRetrofit() }
    single { get<Retrofit>().create(GithubApi::class.java) as GithubApi }
}