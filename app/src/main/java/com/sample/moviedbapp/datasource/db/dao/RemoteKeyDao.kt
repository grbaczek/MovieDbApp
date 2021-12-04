package com.sample.moviedbapp.datasource.db.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.sample.moviedbapp.datasource.db.entity.RemoteKey

@Dao
interface RemoteKeyDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertAll(remoteKey: List<RemoteKey>)

    @Query("SELECT * FROM remotekey WHERE tvShowId = :id")
    suspend fun remoteKeysTvShow(id: Long): RemoteKey?

    @Query("DELETE FROM remotekey")
    suspend fun clearRemoteKeys()
}