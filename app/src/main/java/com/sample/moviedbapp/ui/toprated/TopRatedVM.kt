package com.sample.moviedbapp.ui.toprated

import androidx.lifecycle.ViewModel
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.sample.moviedbapp.datasource.TvShowRepository
import com.sample.moviedbapp.datasource.api.TvShowApi
import com.sample.moviedbapp.datasource.db.QueryIds.TOP_RATED_QUERY_ID
import com.sample.moviedbapp.datasource.db.dao.TvShowRequestPageDao
import com.sample.moviedbapp.datasource.db.entity.TvShow
import com.sample.moviedbapp.ui.ExampleRemoteMediator
import kotlinx.coroutines.flow.Flow

@ExperimentalPagingApi
class TopRatedVM(
    private val tvShowRepository: TvShowRepository,
    private val tvShowRequestPageDao: TvShowRequestPageDao,
    private val tvShowApi: TvShowApi
) : ViewModel() {

    fun fetchData(): Flow<PagingData<TvShow>> {
        return Pager(
            config = PagingConfig(
                pageSize = 20
            ),
            remoteMediator = ExampleRemoteMediator(tvShowRepository)
        ) {
            tvShowRequestPageDao.getTvShowPagingSource(TOP_RATED_QUERY_ID)
        }.flow
    }

}