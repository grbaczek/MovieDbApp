package com.sample.moviedbapp.datasource.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class TvShowsPageResponse(
    val page: Long,
    @SerialName("total_results")
    val totalResults: Long,
    @SerialName("total_pages")
    val totalPages: Long,
    val results: List<TvShowResponse>
)