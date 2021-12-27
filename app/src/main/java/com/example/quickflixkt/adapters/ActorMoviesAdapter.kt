package com.example.quickflixkt.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.quickflixkt.databinding.ActorMoviesRowBinding
import com.example.quickflixkt.models.ActorMovie

class ActorMoviesAdapter(private val clickListener: (ActorMovie) -> Unit): ListAdapter<ActorMovie, ActorMoviesAdapter.MyViewHolder>(DiffCallback) {

    class MyViewHolder(private val binding: ActorMoviesRowBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(actorMovie: ActorMovie) {
            binding.movie = actorMovie
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        return MyViewHolder(
            ActorMoviesRowBinding.inflate(
                LayoutInflater.from(
                    parent.context
                )
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val actorMovie = getItem(position)
        holder.itemView.setOnClickListener {
            clickListener(actorMovie)
        }
        holder.bind(actorMovie)
    }

    companion object {
        private val DiffCallback = object : DiffUtil.ItemCallback<ActorMovie>() {
            override fun areItemsTheSame(oldItem: ActorMovie, newItem: ActorMovie): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: ActorMovie, newItem: ActorMovie): Boolean {
                return oldItem.id == newItem.id
            }

        }
    }
}