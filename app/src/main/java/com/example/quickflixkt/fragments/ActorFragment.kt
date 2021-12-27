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
import com.example.quickflixkt.adapters.ActorMoviesAdapter
import com.example.quickflixkt.database.QuickFlix
import com.example.quickflixkt.databinding.FragmentActorBinding
import com.example.quickflixkt.repositories.ActorMoviesRepository
import com.example.quickflixkt.repositories.ActorsRepository
import com.example.quickflixkt.viewmodels.ActorMoviesViewModel
import com.example.quickflixkt.viewmodels.ActorMoviesViewModelFactory
import com.example.quickflixkt.viewmodels.ActorsViewModel
import com.example.quickflixkt.viewmodels.ActorsViewModelFactory

class ActorFragment : Fragment() {
    private val navigationArgs: ActorFragmentArgs by navArgs()
    private lateinit var _binding: FragmentActorBinding
    private val binding get() = _binding

    private val actorViewModel: ActorsViewModel by viewModels {
        ActorsViewModelFactory (
            ActorsRepository(
                navigationArgs.actorId,
                (activity?.application as QuickFlix).database.actorDao()
            )
        )
    }

    private val actorMoviesViewModel: ActorMoviesViewModel by viewModels {
        ActorMoviesViewModelFactory (
            ActorMoviesRepository (
                navigationArgs.actorId,
                (activity?.application as QuickFlix).database.actorMoviesDao()
            )
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentActorBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        (activity as MainActivity).supportActionBar?.title = navigationArgs.actorName
        (activity as MainActivity).supportActionBar?.setHomeAsUpIndicator(R.drawable.ic_arrow_back)

        binding.lifecycleOwner = this
        binding.actorMoviesViewModel = actorMoviesViewModel
        binding.actorViewModel = actorViewModel

        binding.moviesByList.adapter = ActorMoviesAdapter{
            val action = ActorFragmentDirections.actionActorFragmentToMovieFragment(it.id, it.title!!)
            findNavController().navigate(action)
        }
    }
}