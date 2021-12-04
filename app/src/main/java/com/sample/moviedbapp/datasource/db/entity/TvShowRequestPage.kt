package com.sample.moviedbapp.datasource.db.entity

import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(
    primaryKeys = ["queryId", "totalResults", "totalPages", "page", "tvShowId"],
    foreignKeys = [ForeignKey(
        entity = TvShow::class,
        parentColumns = arrayOf("id"),
        childColumns = arrayOf("tvShowId"),
        onDelete = ForeignKey.CASCADE
    )]
)
data class TvShowRequestPage(
    val queryId: Int,
    val totalResults: Long,
    val totalPages: Long,
    val page: Long,
    val tvShowId: Long,
)