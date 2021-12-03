package com.sample.moviedbapp.ui.toprated

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.sample.moviedbapp.R
import com.sample.moviedbapp.db.TvShow

class TopRatedAdapter :
    PagingDataAdapter<TvShow, TopRatedAdapter.TopRatedTvShowViewHolder>(TvShowComparator) {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): TopRatedTvShowViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.top_rated_tv_show_item, parent, false)
        return TopRatedTvShowViewHolder(view)
    }

    override fun onBindViewHolder(holder: TopRatedTvShowViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }



    object TvShowComparator : DiffUtil.ItemCallback<TvShow>() {
        override fun areItemsTheSame(oldItem: TvShow, newItem: TvShow): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: TvShow, newItem: TvShow): Boolean {
            return oldItem == newItem
        }
    }

    class TopRatedTvShowViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val tvName: TextView = view.findViewById(R.id.tvName)
        val tvOverview: TextView = view.findViewById(R.id.tvOverview)

        fun bind(tvShow: TvShow?) {
            tvShow?.apply {
                tvName.text = tvShow.name
                tvOverview.text = tvShow.overview
            }
        }
    }
}