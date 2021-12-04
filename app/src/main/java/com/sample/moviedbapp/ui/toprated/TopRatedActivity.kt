package com.sample.moviedbapp.ui.toprated

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.view.View
import androidx.lifecycle.lifecycleScope
import androidx.paging.ExperimentalPagingApi
import com.sample.moviedbapp.databinding.ActivityTopRatedBinding
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.distinctUntilChanged
import org.koin.androidx.viewmodel.ext.android.viewModel

//import com.squareup.sqldelight.android.paging3.QueryPagingSource
@ExperimentalPagingApi
class TopRatedActivity : AppCompatActivity() {

    private val topRatedVM: TopRatedVM by viewModel()
    lateinit var binding: ActivityTopRatedBinding
    private lateinit var adapter: TopRatedAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTopRatedBinding.inflate(layoutInflater)
        setContentView(binding.root)


        /*val pagingSource: PagingSource = QueryPagingSource(
            countQuery = playerQueries.countPlayers(),
            transacter = playerQueries,
            dispatcher = Dispatchers.IO,
            queryProvider = playerQueries::players,
        )*/
    }

    override fun onResume() {
        super.onResume()
        initAdapter()
    }



    private fun initAdapter() {
        adapter = TopRatedAdapter()
        binding.topRatedList.adapter = adapter

        lifecycleScope.launchWhenCreated {
            topRatedVM.fetchData().distinctUntilChanged().collectLatest {
                adapter.submitData(it)
            }
        }
    }
}