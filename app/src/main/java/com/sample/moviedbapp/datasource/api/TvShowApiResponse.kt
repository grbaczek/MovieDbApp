package com.sample.moviedbapp.datasource.api

import com.google.gson.annotations.SerializedName


data class TvShowApiResponse(
    val id: Long,
    @SerializedName("vote_average")
    val voteAverage: Double,
    val name: String,
    val overview: String,
    @SerializedName("poster_path")
    val posterPath: String,
    @SerializedName("backdrop_path")
    val backdropPath: String?
)