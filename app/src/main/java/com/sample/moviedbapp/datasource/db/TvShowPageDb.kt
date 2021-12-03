package com.sample.moviedbapp.datasource.db

import androidx.paging.PagingSource
import com.sample.moviedbapp.datasource.api.TvShowApiResponse
import com.sample.moviedbapp.db.TopRatedPagingRemoteKey
import com.sample.moviedbapp.db.TvShow

interface TvShowPageDb {
    suspend fun insertOrIgnore(
        queryId: Long,
        totalResults: Long,
        totalPages: Long,
        page: Long,
        apiTvShows: List<TvShowApiResponse>
    ): List<Long>

    suspend fun count(queryId: Long): Long
    suspend fun clearAll()
    suspend fun clearRemoteKeys()
    suspend fun insertRemoteKeys(keys: List<TopRatedPagingRemoteKey>)
    suspend fun getRemoteKey(tvShowDbId: Long): TopRatedPagingRemoteKey
    val topRatedPagingSource: PagingSource<Long, TvShow>

}