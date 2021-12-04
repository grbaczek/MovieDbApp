package com.sample.moviedbapp.datasource

import androidx.paging.PagingSource
import com.sample.moviedbapp.datasource.db.entity.RemoteKey
import com.sample.moviedbapp.datasource.db.entity.TvShow


interface TvShowRepository {
    suspend fun cacheTopRatedTvShowsPage(pageIndex: Long): Pair<List<Long>, Long?>
    suspend fun clearAll()

    suspend fun clearRemoteKeys()
    suspend fun insertRemoteKeys(keys: List<RemoteKey>)
    suspend fun getRemoteKey(tvShowDbId: Long): RemoteKey?

    val topRatedPagingSource: PagingSource<Int, TvShow>
}