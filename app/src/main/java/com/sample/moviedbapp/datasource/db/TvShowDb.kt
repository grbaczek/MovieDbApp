package com.sample.moviedbapp.datasource.db

interface TvShowDb {
    suspend fun insertOrIgnore(
        id: Long,
        posterPath: String,
        backdropPath: String?,
        name: String,
        overview: String,
        voteAverage: Double
    )
}