package com.sample.moviedbapp.datasource


import com.sample.moviedbapp.datasource.api.ApiSetup
import com.sample.moviedbapp.datasource.api.TvShowApi
import com.sample.moviedbapp.datasource.api.TvShowApiKtor
import com.sample.moviedbapp.datasource.db.dao.RemoteKeyDao
import com.sample.moviedbapp.datasource.db.dao.TvShowDao
import com.sample.moviedbapp.datasource.db.dao.TvShowRequestPageDao
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.cache.HttpCache
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import kotlinx.serialization.json.Json
import org.koin.core.module.Module
import org.koin.dsl.module

fun getDataSourceModule(
    tvShowDao: TvShowDao,
    tvShowRequestPageDao: TvShowRequestPageDao,
    remoteKeyDao: RemoteKeyDao
): Module {
    return module {
        single {
            getHttpClient()
        }
        single {
            //parameters should be injected during build time
            ApiSetup(
                apiKey = "6068441eb40eb1a38c189cb521d2ad36",
                baseUrl = "https://api.themoviedb.org/3/tv/",
                language = "en-US"
            )
        }
        single {
            tvShowDao
        }
        single {
            tvShowRequestPageDao
        }
        single {
            remoteKeyDao
        }
        single<TvShowApi> {
            TvShowApiKtor(get(), get())
        }
        single<TvShowRepository> {
            TvShowRepositoryImpl(get(), get(), get(), get())
        }
    }
}

internal fun getHttpClient(): HttpClient {
    val json = Json {
        ignoreUnknownKeys = true
    }
    return HttpClient(Android) {
        install(JsonFeature) {
            serializer = KotlinxSerializer(json)
            install(HttpCache)
        }
    }
}