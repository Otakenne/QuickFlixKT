package com.example.quickflixkt.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.example.quickflixkt.R
import com.example.quickflixkt.activities.MainActivity
import com.example.quickflixkt.adapters.TrendingMoviesAdapter
import com.example.quickflixkt.adapters.UpcomingMoviesAdapter
import com.example.quickflixkt.database.MoviesDatabase
import com.example.quickflixkt.database.QuickFlix
import com.example.quickflixkt.databinding.FragmentHomeBinding
import com.example.quickflixkt.repositories.TrendingMoviesRepository
import com.example.quickflixkt.repositories.UpcomingMoviesRepository
import com.example.quickflixkt.utility.Constants
import com.example.quickflixkt.viewmodels.TrendingMoviesViewModel
import com.example.quickflixkt.viewmodels.TrendingMoviesViewModelFactory
import com.example.quickflixkt.viewmodels.UpcomingMoviesViewModel
import com.example.quickflixkt.viewmodels.UpcomingMoviesViewModelFactory

class HomeFragment : Fragment() {

    private var _binding: FragmentHomeBinding? = null
    private val binding get() = _binding!!

    private val trendingMoviesViewModel: TrendingMoviesViewModel by viewModels {
        TrendingMoviesViewModelFactory(
            TrendingMoviesRepository(
                (activity?.application as QuickFlix).database.trendingMoviesDao()
            )
        )
    }

    private val upcomingMoviesViewModel: UpcomingMoviesViewModel by viewModels {
        UpcomingMoviesViewModelFactory(
            UpcomingMoviesRepository(
                (activity?.application as QuickFlix).database.upcomingMoviesDao()
            )
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as MainActivity).supportActionBar?.title = ""

        binding.lifecycleOwner = this
        binding.defaultImageUrl = Constants.DEFAULT_IMAGE_URL
        binding.trendingMoviesViewModel = trendingMoviesViewModel
        binding.upcomingMoviesViewModel = upcomingMoviesViewModel

        binding.trendingList.adapter = TrendingMoviesAdapter {
            val action = HomeFragmentDirections.actionHomeFragmentToMovieFragment(it.id, it.title)
            findNavController().navigate(action)
        }

        binding.upcomingList.adapter = UpcomingMoviesAdapter {
            val action = HomeFragmentDirections.actionHomeFragmentToMovieFragment(it.id, it.title)
            findNavController().navigate(action)
        }
    }
}