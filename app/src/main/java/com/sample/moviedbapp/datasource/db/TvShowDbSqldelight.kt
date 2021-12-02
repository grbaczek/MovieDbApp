package com.sample.moviedbapp.datasource.db

import com.sample.moviedbapp.Database
import com.sample.moviedbapp.db.TvShow
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class TvShowDbSqldelight(
    private val database: Database,
    private val backgroundDispatcher: CoroutineDispatcher
) : TvShowDb {
    override suspend fun insertOrIgnore(
        id: Long,
        posterPath: String,
        backdropPath: String?,
        name: String,
        overview: String,
        voteAverage: Double
    ) {
        withContext(backgroundDispatcher) {
            database.tvShowQueries.insertOrIgnore(
                TvShow(
                    id = id,
                    posterPath = posterPath,
                    backdropPath = backdropPath,
                    name = name,
                    overview = overview,
                    voteAverage = voteAverage
                )
            )
        }
    }

}