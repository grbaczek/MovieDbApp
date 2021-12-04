package com.sample.moviedbapp.datasource.db.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sample.moviedbapp.datasource.db.entity.TvShow

@Dao
interface TvShowDao {

    @Query("DELETE FROM tvshow")
    suspend fun clearAll()
}