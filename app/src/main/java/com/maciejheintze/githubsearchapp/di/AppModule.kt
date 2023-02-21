package com.maciejheintze.githubsearchapp.di

import androidx.room.Room
import com.google.gson.Gson
import com.google.gson.GsonBuilder
import com.maciejheintze.githubsearchapp.data.providers.RetrofitProviderImpl
import com.maciejheintze.githubsearchapp.data.remote.GithubApi
import com.maciejheintze.githubsearchapp.db.AppDatabase
import com.maciejheintze.githubsearchapp.db.DATABASE_NAME
import com.maciejheintze.githubsearchapp.db.util.Converters
import com.maciejheintze.githubsearchapp.domain.usecase.*
import com.maciejheintze.githubsearchapp.presentation.MainViewModel
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidApplication
import org.koin.androidx.viewmodel.dsl.viewModel
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

    single {
        Room.databaseBuilder(
            androidApplication(),
            AppDatabase::class.java,
            DATABASE_NAME,
        )
            .addTypeConverter(Converters())
            .build()
    }

    single { get<AppDatabase>().getRepositoryDao() }
    single { GetRepositoryUseCase(api = get()) }
    single { GetCommitsUseCase(api = get()) }
    single { SaveRepositoryDetailUseCase(dao = get()) }
    single { GetLocalRepositoryDetailsListUseCase(dao = get()) }
    single { GetRepositoryDetailsUseCase(dao = get()) }

    viewModel {
        MainViewModel(
            getRepositoryUseCase = get(),
            getCommitsUseCase = get(),
            saveRepositoryDetailUseCase = get(),
            getLocalRepositoryDetailsListUseCase = get(),
            getRepositoryDetailsUseCase = get(),
        )
    }
}