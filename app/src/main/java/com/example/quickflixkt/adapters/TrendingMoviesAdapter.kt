package com.example.quickflixkt.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.quickflixkt.databinding.TrendingMoviesRowLayoutBinding
import com.example.quickflixkt.models.TrendingMovie

class TrendingMoviesAdapter(private val clickListener: (TrendingMovie) -> Unit) : ListAdapter<TrendingMovie, TrendingMoviesAdapter.MyViewHolder>(DiffCallback){

    class MyViewHolder(private val binding: TrendingMoviesRowLayoutBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(trendingMovie: TrendingMovie) {
            binding.movie = trendingMovie
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        return MyViewHolder(
            TrendingMoviesRowLayoutBinding.inflate(
                LayoutInflater.from(
                    parent.context
                )
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val trendingMovie = getItem(position)
        holder.itemView.setOnClickListener {
            clickListener(trendingMovie)
        }
        holder.bind(trendingMovie)
    }

    companion object {
        private var DiffCallback = object : DiffUtil.ItemCallback<TrendingMovie>() {
            override fun areItemsTheSame(oldItem: TrendingMovie, newItem: TrendingMovie): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: TrendingMovie,
                newItem: TrendingMovie
            ): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}