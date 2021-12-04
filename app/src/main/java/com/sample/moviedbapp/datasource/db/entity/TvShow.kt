package com.sample.moviedbapp.datasource.db.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TvShow(
    @PrimaryKey val id: Long,
    val posterPath: String,
    val backdropPath: String?,
    val name: String,
    val overview: String,
    val voteAverage: Double
)