package com.sample.moviedbapp.ui

import android.util.Log
import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import com.sample.moviedbapp.datasource.TvShowRepository
import com.sample.moviedbapp.datasource.db.QueryIds
import com.sample.moviedbapp.datasource.db.entity.RemoteKey
import com.sample.moviedbapp.datasource.db.entity.TvShow

import java.io.InvalidObjectException

@ExperimentalPagingApi
class ExampleRemoteMediator(
    private val tvShowRepository: TvShowRepository,

) : RemoteMediator<Int, TvShow>() {

    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, TvShow>
    ): MediatorResult {
        try {
            Log.i(
                "mediator",
                "anchor: " + state.anchorPosition + ", state.pages.size: " + state.pages.size
            )

            val pageKeyData = getKeyPageData(loadType, state)
            val page = when (pageKeyData) {
                is MediatorResult.Success -> {
                    return pageKeyData
                }
                else -> {
                    pageKeyData as Long
                }
            }
            // clear all tables in the database
            if (loadType == LoadType.REFRESH) {
                tvShowRepository.clearAll()
            }

            val response = tvShowRepository.cacheTopRatedTvShowsPage(page)
            val isEndOfList = response.first.size == 0 || response.second == null
            val prevKey = if (page == 1L) null else page - 1
            val nextKey = if (isEndOfList) null else page + 1
            val keys = response.first.map {
                RemoteKey(tvShowId = it, prevKey = prevKey, nextKey = nextKey)
            }
            tvShowRepository.insertRemoteKeys(keys)

            return MediatorResult.Success(endOfPaginationReached = isEndOfList)
        } catch (e: Exception) {
            Log.e("mediatore", e.toString())
            return MediatorResult.Error(e)
        }
    }

    suspend fun getKeyPageData(loadType: LoadType, state: PagingState<Int, TvShow>): Any? {
        return when (loadType) {
            LoadType.REFRESH -> {
                val remoteKeys = getClosestRemoteKey(state)
                remoteKeys?.nextKey?.minus(1) ?: 1
            }
            LoadType.APPEND -> {
                val remoteKeys = getLastRemoteKey(state)
                    ?: throw InvalidObjectException("Remote key should not be null for $loadType")
                remoteKeys.nextKey
            }
            LoadType.PREPEND -> {
                val remoteKeys = getFirstRemoteKey(state)
                    ?: throw InvalidObjectException("Invalid state, key should not be null")
                //end of list condition reached
                remoteKeys.prevKey ?: return MediatorResult.Success(endOfPaginationReached = true)
                remoteKeys.prevKey
            }
        }
    }

    /**
     * get the last remote key inserted which had the data
     */
    private suspend fun getLastRemoteKey(state: PagingState<Int, TvShow>): RemoteKey? {
        return state.pages
            .lastOrNull { it.data.isNotEmpty() }
            ?.data?.lastOrNull()
            ?.let { tvShow -> tvShowRepository.getRemoteKey(tvShow.id) }
    }

    /**
     * get the first remote key inserted which had the data
     */
    private suspend fun getFirstRemoteKey(state: PagingState<Int, TvShow>): RemoteKey? {
        return state.pages
            .firstOrNull() { it.data.isNotEmpty() }
            ?.data?.firstOrNull()
            ?.let { tvShow -> tvShowRepository.getRemoteKey(tvShow.id) }
    }

    /**
     * get the closest remote key inserted which had the data
     */
    private suspend fun getClosestRemoteKey(state: PagingState<Int, TvShow>): RemoteKey? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { dbId ->
                tvShowRepository.getRemoteKey(dbId)
            }
        }
    }


}