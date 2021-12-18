package com.example.quickflixkt.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.quickflixkt.databinding.UpcomingMoviesRowBinding
import com.example.quickflixkt.models.UpcomingMovie

class UpcomingMoviesAdapter(private val clickListener: (UpcomingMovie) -> Unit):
    ListAdapter<UpcomingMovie, UpcomingMoviesAdapter.MyViewHolder>(DiffCallback) {

    class MyViewHolder(private val binding: UpcomingMoviesRowBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(upcomingMovie: UpcomingMovie) {
            binding.movie = upcomingMovie
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        return MyViewHolder(
            UpcomingMoviesRowBinding.inflate(
                LayoutInflater.from(
                    parent.context
                )
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val upcomingMovie = getItem(position)
        holder.itemView.setOnClickListener {
            clickListener(upcomingMovie)
        }
        holder.bind(upcomingMovie)
    }

    companion object {
        private var DiffCallback = object : DiffUtil.ItemCallback<UpcomingMovie>() {
            override fun areItemsTheSame(oldItem: UpcomingMovie, newItem: UpcomingMovie): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: UpcomingMovie,
                newItem: UpcomingMovie
            ): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}