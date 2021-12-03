package com.sample.moviedbapp.datasource.api

interface TvShowApi {
    suspend fun getTopRatedTvShowsPage(page: Long): TvShowsPageApiResponse
    suspend fun getSimilarTvShowsPage(showId: Long, page: Long): TvShowsPageApiResponse
}