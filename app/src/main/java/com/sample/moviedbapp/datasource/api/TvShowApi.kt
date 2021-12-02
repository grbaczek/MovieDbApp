package com.sample.moviedbapp.datasource.api

interface TvShowApi {
    suspend fun getTopRatedTvShowsPage(page: Int): TvShowsPageResponse
    suspend fun getSimilarTvShowsPage(showId: Int, page: Int): TvShowsPageResponse
}