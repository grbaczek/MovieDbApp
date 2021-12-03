package com.sample.moviedbapp

import com.sample.moviedbapp.datasource.TvShowRepositoryImpl
import com.sample.moviedbapp.datasource.api.ApiSetup
import com.sample.moviedbapp.datasource.api.TvShowApiKtor
import com.sample.moviedbapp.datasource.db.TvShowPageSqldelight
import com.sample.moviedbapp.datasource.getHttpClient
import com.squareup.sqldelight.sqlite.driver.JdbcSqliteDriver
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.runBlocking
import org.junit.Test

import org.junit.Assert.*

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

        val tvShowRequestPageSqldelight = TvShowPageSqldelight(database)

        val tvShowRepository = TvShowRepositoryImpl(tvShowApi, tvShowRequestPageSqldelight)
        tvShowRepository.cacheTopRatedTvShowsNextPage()
        tvShowRepository.cacheTopRatedTvShowsNextPage()
        tvShowRepository.cacheTopRatedTvShowsNextPage()
        val count1 = tvShowRequestPageSqldelight.count(1)
        //tvShowRepository.cacheTopRatedTvShowsPage(4)
        val count2 = tvShowRequestPageSqldelight.count(1)
        val lst =  database.tvShowRequestPageQueries.getTopRatedPageLimitOffset(20,0).executeAsList()
        val lst2 =  database.tvShowRequestPageQueries.getTopRatedPageLimitOffset(20,20).executeAsList()
        assert(lst.size == 20)
        assertTrue(count1 == count2)
    }
}