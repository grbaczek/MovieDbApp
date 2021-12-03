package com.sample.moviedbapp.datasource.api

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class TvShowApiResponse(
    val id: Long,
    @SerialName("vote_average")
    val voteAverage: Double,
    val name: String,
    val overview: String,
    @SerialName("poster_path")
    val posterPath: String,
    @SerialName("backdrop_path")
    val backdropPath: String?
)