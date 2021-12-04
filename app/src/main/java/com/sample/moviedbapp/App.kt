package com.sample.moviedbapp

import android.app.Application
import android.content.Context
import androidx.paging.ExperimentalPagingApi
import com.sample.moviedbapp.datasource.api.TvShowApi
import com.sample.moviedbapp.datasource.db.AppDatabase
import com.sample.moviedbapp.datasource.getDataSourceModule
import com.sample.moviedbapp.ui.toprated.TopRatedVM
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.GlobalContext.startKoin
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


class App : Application() {

    val database: AppDatabase by lazy { AppDatabase.getInstance(this) }

    @ExperimentalPagingApi
    override fun onCreate() {
        super.onCreate()

        startKoin {
            modules(
                module {
                    single<Context> {
                        this@App
                    }
                },
                module {
                    viewModel { TopRatedVM(get(), get(), get()) }
                },
                getDataSourceModule(
                    tvShowDao = database.getTvShowDao(),
                    tvShowRequestPageDao = database.getTvShowRequestDao(),
                    remoteKeyDao = database.getRemoteKeyDao(),
                    tvShowApi = getTvShowApi()
                )
            )
        }
    }

    private fun getTvShowApi(): TvShowApi {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://api.themoviedb.org/3/tv/")
            .addConverterFactory(GsonConverterFactory.create())
            .client(OkHttpClient.Builder().build())
            .build()

        return retrofit.create(TvShowApi::class.java)
    }

    /*private fun getHttpLogger(): HttpLoggingInterceptor {
        return HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }
    }

    private fun getOkHttpClient(
        okHttpLogger: HttpLoggingInterceptor = getHttpLogger(),
        okHttpNetworkInterceptor: Interceptor = getOkHttpNetworkInterceptor()
    ): OkHttpClient {
        return OkHttpClient.Builder()
            .addInterceptor(okHttpLogger)
            .addInterceptor(okHttpNetworkInterceptor)
            .build()
    }*/
}