package com.maciejheintze.githubsearchapp.data.providers

import com.google.gson.Gson
import com.maciejheintze.githubsearchapp.common.Constants.BASE_URL
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface RetrofitProvider {
    fun provideRetrofit(): Retrofit
}

class RetrofitProviderImpl(
    private val okHttpClient: OkHttpClient,
    private val gson: Gson,
) : RetrofitProvider {
    override fun provideRetrofit(): Retrofit {
        return Retrofit.Builder()
            .baseUrl(BASE_URL) //TODO - move BASE_URL to gradle
            .client(okHttpClient)
            .addConverterFactory(GsonConverterFactory.create(gson))
            .build()
    }

}