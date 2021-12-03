package com.sample.moviedbapp.datasource.db

import android.util.Log
import androidx.paging.PagingSource
import com.sample.moviedbapp.Database
import com.sample.moviedbapp.datasource.api.TvShowApiResponse
import com.sample.moviedbapp.datasource.db.QueryIds.TOP_RATED_QUERY_ID
import com.sample.moviedbapp.db.TopRatedPagingRemoteKey
import com.sample.moviedbapp.db.TvShow
import com.sample.moviedbapp.db.TvShowRequestPage
import com.squareup.sqldelight.android.paging3.QueryPagingSource
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class TvShowPageSqldelight(
    private val database: Database
) : TvShowPageDb {
    override suspend fun insertOrIgnore(
        queryId: Long,
        totalResults: Long,
        totalPages: Long,
        page: Long,
        apiTvShows: List<TvShowApiResponse>
    ): List<Long> {
        val insertedIds = mutableListOf<Long>()
        //withContext(Dispatchers.IO) {
        database.transaction {
            for (item in apiTvShows) {
                database.tvShowQueries.insertOrIgnore(
                    dbId = null,
                    id = item.id,
                    posterPath = item.posterPath,
                    backdropPath = item.backdropPath,
                    name = item.name,
                    overview = item.overview,
                    voteAverage = item.voteAverage
                )
                Log.i("mediator", "Insert; " + item.name)
                database.tvShowRequestPageQueries.insertOrIgnore(
                    TvShowRequestPage(
                        queryId = queryId,
                        totalResults = totalResults,
                        totalPages = totalPages,
                        page = page,
                        tvShowId = item.id
                    )
                )
                insertedIds.add(database.tvShowQueries.lastInsertRowId().executeAsOne())
            }
        }
        //}
        Log.i("mediator", "after TvShowPageSqldelight insertOrIgnore")
        return insertedIds;
    }

    override suspend fun count(queryId: Long): Long {
        //return withContext(Dispatchers.IO) {
        return database.tvShowRequestPageQueries.count(queryId).executeAsOne()
        //}
    }


    override suspend fun clearAll() {
        //withContext(Dispatchers.IO) {
            database.transaction {
                database.tvShowQueries.clearAll()
            }
        //}
    }

    override suspend fun clearRemoteKeys() {
        //withContext(Dispatchers.IO) {
            database.transaction {
                database.topRatedPagingRemoteKeyQueries.clearRemoteKeys()
            }
        //}
    }

    override suspend fun insertRemoteKeys(keys: List<TopRatedPagingRemoteKey>) {
        //withContext(Dispatchers.IO) {
            database.topRatedPagingRemoteKeyQueries.transaction {
                for (key in keys) {
                    database.topRatedPagingRemoteKeyQueries.insertRemoteKey(
                        tvShowDbId = key.tvShowDbId,
                        prevKey = key.prevKey,
                        nextKey = key.nextKey
                    )
                }
            }
        //}
    }

    override suspend fun getRemoteKey(tvShowDbId: Long): TopRatedPagingRemoteKey {
        //return withContext(Dispatchers.IO) {
          return  database.topRatedPagingRemoteKeyQueries.getRemoteKey(tvShowDbId).executeAsOne()
        //}
    }


    override val topRatedPagingSource: PagingSource<Long, TvShow>
        get() = QueryPagingSource(
            countQuery = database.tvShowRequestPageQueries.count(TOP_RATED_QUERY_ID),
            transacter = database.tvShowRequestPageQueries,
            dispatcher = Dispatchers.Main,
            queryProvider = database.tvShowRequestPageQueries::getTopRatedPageLimitOffset,
        )

}