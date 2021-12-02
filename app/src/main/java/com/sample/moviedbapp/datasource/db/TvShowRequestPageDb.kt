package com.sample.moviedbapp.datasource.db

interface TvShowRequestPageDb {
    suspend fun insertOrIgnore(
        url: String,
        totalResults: Long,
        totalPages: Long,
        page: Long,
        tvShowId: Long
    )
}