package com.pop_flake.home

import android.content.BroadcastReceiver
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.pop_flake.R
import com.pop_flake.adapter.HomeAdapter
import com.pop_flake.checkinternet.CheckInternetService
import com.pop_flake.checkinternet.NetworkUtil
import com.pop_flake.databinding.FragmentHomeBinding
import com.pop_flake.interfaces.ClickedMovie
import com.pop_flake.model.MovieModel
import com.pop_flake.other.Constant.Companion.currentPage
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch


@AndroidEntryPoint
class HomeFragment : Fragment(), ClickedMovie {

    private lateinit var binding: FragmentHomeBinding

    private lateinit var broadcastReceiver: BroadcastReceiver

    val homeViewModel: HomeViewModel by viewModels()

    private lateinit var homeAdapter: HomeAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentHomeBinding.inflate(inflater, container, false)
        return binding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        initBroadCast()
        initAdapter()
        fetchMovies(currentPage)
        fetchMoreMovies()

    }


    private fun initBroadCast() {
        broadcastReceiver = CheckInternetService()
        checkInternet()
    }

    private fun checkInternet() {
        requireActivity().registerReceiver(
            broadcastReceiver,
            IntentFilter(ConnectivityManager.CONNECTIVITY_ACTION)
        )
    }

    private fun initAdapter() {
        homeAdapter = HomeAdapter(this@HomeFragment)
    }

    private fun fetchMovies(currentPage: Int) {
        val status = NetworkUtil.networkState(requireContext())
        if (status.not()) {
            homeViewModel.getMostPopular(currentPage).removeObservers(viewLifecycleOwner)
        } else {
            homeViewModel.getMostPopular(currentPage).observe(viewLifecycleOwner) {
                homeAdapter.differ.submitList(it.movieModel.toList())
                binding.rVMovie.apply {
                    this.adapter = homeAdapter
                    this.layoutManager =
                        LinearLayoutManager(
                            requireContext(),
                            LinearLayoutManager.VERTICAL,
                            false
                        )
                }
            }
        }
    }

    private fun fetchMoreMovies() {
        binding.loadingMoreMovie.setOnRefreshListener {
            lifecycleScope.launch {
                currentPage++
                binding.loadingMoreMovie.isRefreshing = false
                fetchMovies(currentPage)
                delay(3000)
            }
        }
    }

    override fun clickedMovieToDetail(movieModel: MovieModel) {
        val bundle = Bundle().apply {
            this.putSerializable("Movie To Details", movieModel)
        }
        findNavController().navigate(R.id.action_homeFragment_to_movieDetailFragment, bundle)
    }
}