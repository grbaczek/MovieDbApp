package com.sample.moviedbapp.datasource.db.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Transaction
import com.sample.moviedbapp.datasource.api.TvShowApiResponse
import com.sample.moviedbapp.datasource.db.entity.TvShow
import com.sample.moviedbapp.datasource.db.entity.TvShowRequestPage

@Dao
abstract class TvShowRequestPageDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun insertTvShow(tvShows: TvShow)

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    abstract suspend fun insertTvShowRequestPage(tvShows: TvShowRequestPage)

    @Transaction
    open suspend fun insertAll(
        tvShows: List<TvShowApiResponse>,
        queryId: Int,
        totalResults: Long,
        totalPages: Long,
        page: Long
    ) {
        for (tvShowApi in tvShows) {
            insertTvShow(
                TvShow(
                    id = tvShowApi.id,
                    posterPath = tvShowApi.posterPath,
                    backdropPath = tvShowApi.backdropPath,
                    name = tvShowApi.name,
                    overview = tvShowApi.overview,
                    voteAverage = tvShowApi.voteAverage
                )
            )
        }
    }

    @Query(
        """SELECT  *
            FROM TvShow
            INNER JOIN TvShowRequestPage ON TvShowRequestPage.tvShowId = TvShow.id
            WHERE TvShowRequestPage.queryId = :queryId"""
    )
    abstract fun getTvShowPagingSource(queryId: Int): PagingSource<Int, TvShow>
}