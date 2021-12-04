package com.sample.moviedbapp.datasource.api

import com.google.gson.annotations.SerializedName

data class TvShowsPageApiResponse(
    val page: Long,
    @SerializedName("total_results")
    val totalResults: Long,
    @SerializedName("total_pages")
    val totalPages: Long,
    val results: List<TvShowApiResponse>
)