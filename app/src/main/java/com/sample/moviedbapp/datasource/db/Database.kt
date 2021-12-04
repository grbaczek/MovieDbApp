package com.sample.moviedbapp.datasource.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.sample.moviedbapp.datasource.db.dao.RemoteKeyDao
import com.sample.moviedbapp.datasource.db.dao.TvShowDao
import com.sample.moviedbapp.datasource.db.dao.TvShowRequestPageDao
import com.sample.moviedbapp.datasource.db.entity.RemoteKey
import com.sample.moviedbapp.datasource.db.entity.TvShow
import com.sample.moviedbapp.datasource.db.entity.TvShowRequestPage

@Database(version = 1, entities = [RemoteKey::class, TvShow::class, TvShowRequestPage::class], exportSchema = false)
abstract class AppDatabase : RoomDatabase() {

    abstract fun getRemoteKeyDao(): RemoteKeyDao
    abstract fun getTvShowDao(): TvShowDao
    abstract fun getTvShowRequestDao(): TvShowRequestPageDao

    companion object {

        val TV_SHOWS_DB: String? = null

        @Volatile
        private var INSTANCE: AppDatabase? = null

        fun getInstance(context: Context): AppDatabase =
            INSTANCE ?: synchronized(this) {
                INSTANCE
                    ?: buildDatabase(context).also { INSTANCE = it }
            }

        private fun buildDatabase(context: Context) =
            Room.inMemoryDatabaseBuilder(context.applicationContext, AppDatabase::class.java)
                .build()
    }

}