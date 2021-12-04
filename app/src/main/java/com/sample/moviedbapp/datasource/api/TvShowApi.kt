package com.sample.moviedbapp.datasource.api

import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query


interface TvShowApi {
    @GET("top_rated")
    suspend fun getTopRatedTvShowsPage(
        @Query("api_key") apiKey: String,
        @Query("page") page: Long
    ): TvShowsPageApiResponse

    @GET("{showId}/similar")
    suspend fun getSimilarTvShowsPage(
        @Query("api_key") apiKey: String,
        @Path("api_key") showId: Long,
        @Query("page") page: Long
    ): TvShowsPageApiResponse
}