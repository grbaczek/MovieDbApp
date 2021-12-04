package com.sample.moviedbapp.ui.toprated

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.paging.ExperimentalPagingApi
import com.sample.moviedbapp.databinding.ActivityTopRatedBinding

//import com.squareup.sqldelight.android.paging3.QueryPagingSource
@ExperimentalPagingApi
class TopRatedActivity : AppCompatActivity() {

    lateinit var binding: ActivityTopRatedBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityTopRatedBinding.inflate(layoutInflater)
        setContentView(binding.root)

    }

}