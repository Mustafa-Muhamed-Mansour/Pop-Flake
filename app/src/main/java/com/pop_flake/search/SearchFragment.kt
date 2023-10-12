package com.pop_flake.search

import android.content.BroadcastReceiver
import android.content.IntentFilter
import android.net.ConnectivityManager
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.pop_flake.R
import com.pop_flake.adapter.HomeAdapter
import com.pop_flake.adapter.SearchAdapter
import com.pop_flake.checkinternet.CheckInternetService
import com.pop_flake.checkinternet.NetworkUtil
import com.pop_flake.databinding.FragmentSearchBinding
import com.pop_flake.interfaces.ClickedMovie
import com.pop_flake.model.MovieModel
import com.pop_flake.other.Validation
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class SearchFragment : Fragment(), ClickedMovie {

    private lateinit var binding: FragmentSearchBinding

    private lateinit var broadcastReceiver: BroadcastReceiver

    val SearchViewModel: SearchViewModel by viewModels()

    private lateinit var searchAdapter: SearchAdapter

    @Inject
    lateinit var validation: Validation

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentSearchBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        initBroadCast()
        initAdapter()
        clickedView()

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
        searchAdapter = SearchAdapter(this@SearchFragment)
    }

    private fun clickedView() {
        binding.imgSearchMovie.setOnClickListener {
            val search = binding.editSearch.text.trim().toString()
            checkValidation(search)
        }
    }

    private fun checkValidation(search: String) {
        if (validation.checkSearchMovieName(search).none()) {
            binding.editSearch.requestFocus()
        } else {
            val status = NetworkUtil.networkState(requireContext())
            if (status.not()) {
                SearchViewModel.getSearchMovies(search).removeObservers(viewLifecycleOwner)
            } else {
                fetchMovieToSearch(search)
            }
        }
    }


    private fun fetchMovieToSearch(search: String) {
        lifecycleScope.launch {
            binding.loadingSearch.visibility = View.VISIBLE
            delay(3000)
            SearchViewModel.getSearchMovies(search).observe(viewLifecycleOwner) {
                searchAdapter.differ.submitList(it.movieModel.toList())

                binding.rVSearch.apply {
                    this.adapter = searchAdapter
                    this.layoutManager =
                        LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
                }
                binding.loadingSearch.visibility = View.GONE
            }
        }
    }

    override fun clickedMovieToDetail(movieModel: MovieModel) {
        val bundle = Bundle().apply {
            this.putSerializable("Movie To Details", movieModel)
        }
        findNavController().navigate(R.id.action_searchFragment_to_movieDetailFragment, bundle)
    }
}