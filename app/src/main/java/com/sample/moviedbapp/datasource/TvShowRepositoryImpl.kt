package com.sample.moviedbapp.datasource

import android.util.Log
import androidx.paging.PagingSource
import com.sample.moviedbapp.datasource.api.TvShowApi
import com.sample.moviedbapp.datasource.db.QueryIds.TOP_RATED_QUERY_ID
import com.sample.moviedbapp.datasource.db.TvShowPageDb
import com.sample.moviedbapp.db.TopRatedPagingRemoteKey
import com.sample.moviedbapp.db.TvShow


class TvShowRepositoryImpl(
    private val tvShowApi: TvShowApi,
    private val tvShowPageDb: TvShowPageDb,
) : TvShowRepository {
    override suspend fun cacheTopRatedTvShowsPage(pageIndex: Long): Pair<List<Long>, Long?> {
        val apiPage = tvShowApi.getTopRatedTvShowsPage(pageIndex)
        val insertedIds = tvShowPageDb.insertOrIgnore(
            queryId = TOP_RATED_QUERY_ID,
            totalPages = apiPage.totalPages,
            totalResults = apiPage.totalResults,
            page = apiPage.page,
            apiTvShows = apiPage.results
        )
        Log.i("mediator", "cacheTopRatedTvShowsPage - after inster")
        return Pair(insertedIds, if (apiPage.page < apiPage.totalPages) apiPage.page + 1L else null)
    }

    override suspend fun clearAll() {
        tvShowPageDb.clearAll()
    }

    override suspend fun clearRemoteKeys() {
        tvShowPageDb.clearRemoteKeys()
    }

    override suspend fun insertRemoteKeys(keys: List<TopRatedPagingRemoteKey>) {
        tvShowPageDb.insertRemoteKeys(keys)
    }

    override suspend fun getRemoteKey(tvShowDbId: Long): TopRatedPagingRemoteKey {
        return tvShowPageDb.getRemoteKey(tvShowDbId)
    }

    override val topRatedPagingSource: PagingSource<Long, TvShow>
        get() = tvShowPageDb.topRatedPagingSource

}