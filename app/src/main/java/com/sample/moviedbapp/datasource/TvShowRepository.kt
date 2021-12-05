package com.sample.moviedbapp.datasource

import androidx.paging.PagingSource
import com.sample.moviedbapp.datasource.api.TvShowsPageApiResponse
import com.sample.moviedbapp.datasource.db.entity.RemoteKey
import com.sample.moviedbapp.datasource.db.entity.TvShow


interface TvShowRepository {
    suspend fun getTopRatedPage(pageIndex: Long): TvShowsPageApiResponse
    suspend fun insertTopRatedPage(
        tvShowsPageApiResponse: TvShowsPageApiResponse,
        remoteKeys: List<RemoteKey>
    )

    suspend fun clearAll()

    suspend fun clearRemoteKeys()
    suspend fun insertRemoteKeys(keys: List<RemoteKey>)
    suspend fun getRemoteKey(tvShowDbId: Long): RemoteKey?

    val topRatedPagingSource: PagingSource<Int, TvShow>
}