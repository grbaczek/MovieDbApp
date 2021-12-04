package com.sample.moviedbapp.datasource

import android.util.Log
import androidx.paging.PagingSource
import com.sample.moviedbapp.datasource.api.TvShowApi
import com.sample.moviedbapp.datasource.db.QueryIds.TOP_RATED_QUERY_ID
import com.sample.moviedbapp.datasource.db.dao.RemoteKeyDao
import com.sample.moviedbapp.datasource.db.dao.TvShowDao
import com.sample.moviedbapp.datasource.db.dao.TvShowRequestPageDao
import com.sample.moviedbapp.datasource.db.entity.RemoteKey
import com.sample.moviedbapp.datasource.db.entity.TvShow


class TvShowRepositoryImpl(
    private val tvShowApi: TvShowApi,
    private val tvShowDao: TvShowDao,
    private val tvShowRequestPageDao: TvShowRequestPageDao,
    private val remoteKeyDao: RemoteKeyDao
) : TvShowRepository {
    override suspend fun cacheTopRatedTvShowsPage(pageIndex: Long): Pair<List<Long>, Long?> {
        var apiPage = tvShowApi.getTopRatedTvShowsPage(
            apiKey = apiKey,
            page = pageIndex
        )
        //Log.i("mediator", "web response received: " + apiPage.page )
        /*val apiPage = TvShowsPageApiResponse(
            page = pageIndex,
            totalResults = 10000,
            totalPages = 100,
            results = (1..20).map {
                TvShowApiResponse(
                    id= pageIndex * 100 + it,
                    voteAverage = 8.7,
                    name = "name" + it,
                    overview = "overview",
                    posterPath = "",
                    backdropPath = ""
                )
            }
        )*/

        tvShowRequestPageDao.insertAll(
            queryId = TOP_RATED_QUERY_ID,
            totalPages = apiPage.totalPages,
            totalResults = apiPage.totalResults,
            page = apiPage.page,
            tvShows = apiPage.results
        )
        Log.i("mediator", "cacheTopRatedTvShowsPage - after instert")
        return Pair(
            apiPage.results.map { it.id },
            if (apiPage.page < apiPage.totalPages) apiPage.page + 1L else null
        )
    }

    override suspend fun clearAll() {
        tvShowDao.clearAll()
    }

    override suspend fun clearRemoteKeys() {
        remoteKeyDao.clearRemoteKeys()
    }

    override suspend fun insertRemoteKeys(keys: List<RemoteKey>) {
        remoteKeyDao.insertAll(keys)
    }

    override suspend fun getRemoteKey(tvShowDbId: Long): RemoteKey? {
        return remoteKeyDao.remoteKeysTvShow(tvShowDbId)
    }

    override val topRatedPagingSource: PagingSource<Int, TvShow>
        get() = tvShowRequestPageDao.getTvShowPagingSource(TOP_RATED_QUERY_ID)

    companion object {
        val apiKey = "6068441eb40eb1a38c189cb521d2ad36"
    }

}