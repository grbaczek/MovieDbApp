package com.sample.moviedbapp.datasource.api

import io.ktor.client.HttpClient
import io.ktor.client.request.get

class TvShowApiKtor(private val apiSetup: ApiSetup, private val httpClient: HttpClient,) : TvShowApi {
    override suspend fun getTopRatedTvShowsPage(page: Int): TvShowsPageResponse {
        return httpClient.get("${apiSetup.baseUrl}top_rated?api_key=${apiSetup.apiKey}&language=${apiSetup.language}&page=${page}")
    }

    override suspend fun getSimilarTvShowsPage(showId: Int, page: Int): TvShowsPageResponse {
        return httpClient.get("${apiSetup.baseUrl}$showId/similar?api_key=${apiSetup.apiKey}&language=${apiSetup.language}&page=${page}")
    }
}