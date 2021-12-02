package com.sample.moviedbapp

import android.provider.ContactsContract
import com.sample.moviedbapp.datasource.api.ApiSetup
import com.sample.moviedbapp.datasource.api.TvShowApiKtor
import com.sample.moviedbapp.datasource.db.TvShowDbSqldelight
import com.sample.moviedbapp.datasource.db.TvShowRequestPageSqldelight
import com.sample.moviedbapp.datasource.getHttpClient
import com.squareup.sqldelight.sqlite.driver.JdbcSqliteDriver
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*
import org.junit.Before

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {

    @Test
    fun addition_isCorrect() = runBlocking {
        val dbDriver = JdbcSqliteDriver(JdbcSqliteDriver.IN_MEMORY)
        Database.Schema.create(dbDriver)
        val database = Database(dbDriver)
        val httpClient = getHttpClient()
        val apiSetup = ApiSetup(
            apiKey = "6068441eb40eb1a38c189cb521d2ad36",
            baseUrl = "https://api.themoviedb.org/3/tv/",
            language = "en-US"
        )
        val tvShowApi = TvShowApiKtor(apiSetup, httpClient)
        val tvShowDbSqldelight = TvShowDbSqldelight(database, Dispatchers.Default)
        val tvShowRequestPageSqldelight = TvShowRequestPageSqldelight(database, Dispatchers.Default)
        val page = tvShowApi.getTopRatedTvShowsPage(1)
        val page2 = tvShowApi.getSimilarTvShowsPage(1,1)

        for (tvShow in page.results){
            tvShowDbSqldelight.insertOrIgnore(
                id = tvShow.id,
                posterPath = tvShow.posterPath,
                backdropPath = tvShow.backdropPath,
                name = tvShow.name,
                voteAverage = tvShow.voteAverage,
                overview = tvShow.overview
            )
            tvShowRequestPageSqldelight.insertOrIgnore(
                url = "toprated",
                totalResults = page.totalResults,
                totalPages = page.totalPages,
                page = page.page,
                tvShowId = tvShow.id
            )
        }
        for (tvShow in page2.results){
            tvShowDbSqldelight.insertOrIgnore(
                id = tvShow.id,
                posterPath = tvShow.posterPath,
                backdropPath = tvShow.backdropPath,
                name = tvShow.name,
                voteAverage = tvShow.voteAverage,
                overview = tvShow.overview
            )
            tvShowRequestPageSqldelight.insertOrIgnore(
                url = "toprated",
                totalResults = page.totalResults,
                totalPages = page.totalPages,
                page = page.page,
                tvShowId = tvShow.id
            )
        }

        assertTrue(page.totalPages > 1)
        assertTrue(page2.totalPages > 1)
    }
}