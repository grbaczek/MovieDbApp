package com.sample.moviedbapp.ui.toprated

import androidx.lifecycle.ViewModel
import androidx.paging.ExperimentalPagingApi
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import com.sample.moviedbapp.datasource.TvShowRepository
import com.sample.moviedbapp.db.TvShow
import com.sample.moviedbapp.ui.ExampleRemoteMediator
import kotlinx.coroutines.flow.Flow

@ExperimentalPagingApi
class TopRatedVM(tvShowRepository: TvShowRepository) : ViewModel() {
    val topRatedPagingData: Flow<PagingData<TvShow>> = Pager(
        config = PagingConfig(
            pageSize = 20
        ),
        remoteMediator = ExampleRemoteMediator(tvShowRepository)
    ) {
        tvShowRepository.topRatedPagingSource
    }.flow
}