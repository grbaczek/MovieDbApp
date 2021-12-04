package com.sample.moviedbapp.datasource


import com.sample.moviedbapp.datasource.api.TvShowApi
import com.sample.moviedbapp.datasource.db.dao.RemoteKeyDao
import com.sample.moviedbapp.datasource.db.dao.TvShowDao
import com.sample.moviedbapp.datasource.db.dao.TvShowRequestPageDao
import org.koin.core.module.Module
import org.koin.dsl.module

fun getDataSourceModule(
    tvShowDao: TvShowDao,
    tvShowRequestPageDao: TvShowRequestPageDao,
    remoteKeyDao: RemoteKeyDao,
    tvShowApi: TvShowApi
): Module {
    return module {
        single {
            tvShowApi
        }
        single {
            tvShowDao
        }
        single {
            tvShowRequestPageDao
        }
        single {
            remoteKeyDao
        }
        single<TvShowRepository> {
            TvShowRepositoryImpl(get(), get(), get(), get())
        }
    }
}
