package com.example.quickflixkt.adapters

import android.view.View
import android.view.View.GONE
import android.view.View.VISIBLE
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.net.toUri
import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.example.quickflixkt.models.*

@BindingAdapter("imageUrl")
fun loadImage(
    imageView: ImageView,
    imageUrl: String?
) {
    imageUrl?.let {
        val modifiedImageUrl = "https://image.tmdb.org/t/p/original/$imageUrl"
        val imageUri = modifiedImageUrl.toUri().buildUpon().scheme("https").build()
        imageView.load(imageUri) {}
    }
}

@BindingAdapter("headerImageUrl")
fun loadHeaderImage(
    imageView: ImageView,
    movieList: List<TrendingMovie>?
) {
    if (movieList != null) {
        if (movieList.isNotEmpty()) {
            movieList[0].backdrop_path?.let {
                val modifiedImageUrl =
                    "https://image.tmdb.org/t/p/original/${movieList[0].backdrop_path}"
                val imageUri = modifiedImageUrl.toUri().buildUpon().scheme("https").build()
                imageView.load(imageUri) {}
            }
        }
    }
}

@BindingAdapter("clipToOutline")
fun clipToOutline(
    linearLayout: LinearLayout,
    clip: Boolean?
) {
    clip?.let {
        linearLayout.clipToOutline = clip
    }
}

@BindingAdapter("trendingMoviesListData")
fun bindRecyclerViewTrendingMovies(
    recyclerView: RecyclerView,
    data: List<TrendingMovie>?
) {
    val adapter = recyclerView.adapter as TrendingMoviesAdapter
    adapter.submitList(data)
}

@BindingAdapter("upcomingMoviesListData")
fun bindRecyclerViewUpcomingMovies(
    recyclerView: RecyclerView,
    data: List<UpcomingMovie>?
) {
    val adapter = recyclerView.adapter as UpcomingMoviesAdapter
    adapter.submitList(data)
}

@BindingAdapter("actorMoviesListData")
fun bindRecyclerViewActorMovies(
    recyclerView: RecyclerView,
    data: List<ActorMovie>?
) {
    val adapter = recyclerView.adapter as ActorMoviesAdapter
    adapter.submitList(data)
}

@BindingAdapter("movieCreditsListData")
fun bindRecyclerViewMovieCredits(
    recyclerView: RecyclerView,
    data: List<MovieCredit>?
) {
    val adapter = recyclerView.adapter as MovieCreditsAdapter
    adapter.submitList(data)
}

@BindingAdapter("similarMovieListData")
fun bindRecyclerViewSimilarMovies(
    recyclerView: RecyclerView,
    data: List<SimilarMovies>?
) {
    val adapter = recyclerView.adapter as SimilarMoviesAdapter
    adapter.submitList(data)
}

@BindingAdapter("visibility")
fun isVisible(view: View, visible: Boolean) {
    when (visible) {
        true -> view.visibility = VISIBLE
        else -> view.visibility = GONE
    }
}

