package com.example.quickflixkt.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.quickflixkt.databinding.ActorRowBinding
import com.example.quickflixkt.models.MovieCredit

class MovieCreditsAdapter(private val clickListener: (MovieCredit) -> Unit):
    ListAdapter<MovieCredit, MovieCreditsAdapter.MyViewHolder>(DiffCallback){

    class MyViewHolder(private val binding: ActorRowBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(movieCredit: MovieCredit) {
            binding.actor = movieCredit
            binding.executePendingBindings()
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MyViewHolder {
        return MyViewHolder(
            ActorRowBinding.inflate(
                LayoutInflater.from(
                    parent.context
                )
            )
        )
    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val movieCredit = getItem(position)
        holder.itemView.setOnClickListener {
            clickListener(movieCredit)
        }
        holder.bind(movieCredit)
    }

    override fun getItemCount(): Int {
        return if (super.getItemCount() > 6) {
            6
        } else {
            super.getItemCount()
        }
    }

    companion object {
        private var DiffCallback = object: DiffUtil.ItemCallback<MovieCredit>() {
            override fun areItemsTheSame(oldItem: MovieCredit, newItem: MovieCredit): Boolean {
                return oldItem == newItem
            }

            override fun areContentsTheSame(oldItem: MovieCredit, newItem: MovieCredit): Boolean {
                return oldItem.id == newItem.id
            }
        }
    }
}