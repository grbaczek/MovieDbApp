package com.sample.moviedbapp

import android.app.Application
import android.content.Context
import androidx.paging.ExperimentalPagingApi
import com.sample.moviedbapp.datasource.db.AppDatabase
import com.sample.moviedbapp.datasource.getDataSourceModule
import com.sample.moviedbapp.ui.toprated.TopRatedVM
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.GlobalContext.startKoin
import org.koin.dsl.module

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

                    viewModel { TopRatedVM(get()) }
                },
                getDataSourceModule(
                    tvShowDao = database.getTvShowDao(),
                    tvShowRequestPageDao = database.getTvShowRequestDao(),
                    remoteKeyDao = database.getRemoteKeyDao()
                )
            )
        }

    }
}