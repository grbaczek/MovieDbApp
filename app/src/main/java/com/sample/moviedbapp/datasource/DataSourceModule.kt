package com.sample.moviedbapp.datasource

import com.sample.moviedbapp.datasource.api.ApiSetup
import com.sample.moviedbapp.datasource.api.TvShowApi
import com.sample.moviedbapp.datasource.api.TvShowApiKtor
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.features.cache.HttpCache
import io.ktor.client.features.json.JsonFeature
import io.ktor.client.features.json.serializer.KotlinxSerializer
import kotlinx.serialization.json.Json
import org.koin.core.module.Module
import org.koin.dsl.module

fun getDataSourceModule(): Module {
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
        single<TvShowApi> {
            TvShowApiKtor(get(), get())
        }
    }
}

internal fun getHttpClient(): HttpClient {
    val json = Json {
        ignoreUnknownKeys = true
        /*parse numbers to strings. BigDecimal -> String*/
        isLenient = true
    }
    return HttpClient(Android) {
        install(JsonFeature) {
            serializer = KotlinxSerializer(json)
            install(HttpCache)
        }
    }
}