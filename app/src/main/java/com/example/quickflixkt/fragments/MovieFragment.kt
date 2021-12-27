package com.example.quickflixkt.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.quickflixkt.R
import com.example.quickflixkt.activities.MainActivity
import com.example.quickflixkt.adapters.MovieCreditsAdapter
import com.example.quickflixkt.adapters.SimilarMoviesAdapter
import com.example.quickflixkt.database.QuickFlix
import com.example.quickflixkt.databinding.FragmentMovieBinding
import com.example.quickflixkt.repositories.MovieCreditsRepository
import com.example.quickflixkt.repositories.MoviesRepository
import com.example.quickflixkt.repositories.SimilarMoviesRepository
import com.example.quickflixkt.viewmodels.*

class MovieFragment : Fragment() {
    private val navigationArgs: MovieFragmentArgs by navArgs()
    private var _binding: FragmentMovieBinding? = null
    private val binding get() = _binding!!

    private val movieViewModel: MoviesViewModel by viewModels {
        MoviesViewModelFactory(
            MoviesRepository(
                navigationArgs.movieId,
                (activity?.application as QuickFlix).database.movieDao()
            )
        )
    }

    private val movieCreditsViewModel: MovieCreditsViewModel by viewModels {
        MovieCreditsViewModelFactory(
            MovieCreditsRepository(
                navigationArgs.movieId,
                (activity?.application as QuickFlix).database.movieCreditDao()
            )
        )
    }

    private val similarMoviesViewModel: SimilarMoviesViewModel by viewModels {
        SimilarMoviesViewModelFactory(
            SimilarMoviesRepository(
                navigationArgs.movieId,
                (activity?.application as QuickFlix).database.similarMoviesDao()
            )
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentMovieBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as MainActivity).supportActionBar?.title = navigationArgs.movieName
//        (activity as MainActivity).supportActionBar?.
        (activity as MainActivity).supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back)

        binding.lifecycleOwner = this
        binding.movieViewModel = movieViewModel
        binding.movieCreditsViewModel = movieCreditsViewModel
        binding.similarMoviesViewModel = similarMoviesViewModel
        binding.holderText = "Some Text"

        binding.creditList.adapter = MovieCreditsAdapter {
            val action = MovieFragmentDirections.actionMovieFragmentToActorFragment(it.id, it.original_name!!)
            findNavController().navigate(action)
        }

        binding.similarList.adapter = SimilarMoviesAdapter{
            val action = MovieFragmentDirections.actionMovieFragmentSelf(it.id, it.title!!)
            findNavController().navigate(action)
        }
    }
}