package com.example.quickflixkt.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.quickflixkt.databinding.SimilarMoviesRowBinding
import com.example.quickflixkt.models.SimilarMovies

class SimilarMoviesAdapter(private val clickListener: (SimilarMovies) -> Unit):
    ListAdapter<SimilarMovies, SimilarMoviesAdapter.MyViewHolder>(DiffCallback) {

    class MyViewHolder(private val binding: SimilarMoviesRowBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(similarMovies: SimilarMovies) {
            binding.movie = similarMovies
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        return MyViewHolder(
            SimilarMoviesRowBinding.inflate(
                LayoutInflater.from(
                    parent.context
                )
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val similarMovies = getItem(position)
        holder.itemView.setOnClickListener {
            clickListener(similarMovies)
        }
        holder.bind(similarMovies)
    }

    companion object {
        var DiffCallback = object : DiffUtil.ItemCallback<SimilarMovies>() {
            override fun areItemsTheSame(oldItem: SimilarMovies, newItem: SimilarMovies): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(
                oldItem: SimilarMovies,
                newItem: SimilarMovies
            ): Boolean {
                return oldItem.imdb_id == newItem.imdb_id
            }

        }
    }
}
