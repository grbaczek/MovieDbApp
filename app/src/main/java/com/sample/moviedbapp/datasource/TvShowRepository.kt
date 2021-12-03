package com.sample.moviedbapp.datasource

import androidx.paging.PagingSource
import com.sample.moviedbapp.db.TopRatedPagingRemoteKey
import com.sample.moviedbapp.db.TvShow

interface TvShowRepository {
    suspend fun cacheTopRatedTvShowsPage(pageIndex: Long): Pair<List<Long>, Long?>
    suspend fun clearAll()

    suspend fun clearRemoteKeys()
    suspend fun insertRemoteKeys(keys: List<TopRatedPagingRemoteKey>)
    suspend fun getRemoteKey(tvShowDbId: Long): TopRatedPagingRemoteKey

    val topRatedPagingSource: PagingSource<Long, TvShow>
}