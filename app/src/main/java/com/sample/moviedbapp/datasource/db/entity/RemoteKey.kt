package com.sample.moviedbapp.datasource.db.entity

import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey

@Entity(
    foreignKeys = [ForeignKey(
        entity = TvShow::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("tvShowId"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class RemoteKey(
    @PrimaryKey val tvShowId: Long,
    val prevKey: Long?,
    val nextKey: Long?
)