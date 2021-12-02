package com.sample.moviedbapp.datasource.db

import com.sample.moviedbapp.Database
import com.sample.moviedbapp.db.TvShowRequestPage
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext

class TvShowRequestPageSqldelight(
    private val database: Database,
    private val backgroundDispatcher: CoroutineDispatcher
) : TvShowRequestPageDb {
    override suspend fun insertOrIgnore(
        url: String,
        totalResults: Long,
        totalPages: Long,
        page: Long,
        tvShowId: Long
    ) {
        withContext(backgroundDispatcher) {
            database.tvShowRequestPageQueries.insertOrIgnore(
                TvShowRequestPage(
                    url = url,
                    totalResults = totalResults,
                    totalPages = totalPages,
                    page = page,
                    tvShowId = tvShowId
                )
            )
        }
    }
}