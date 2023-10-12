package com.pop_flake.details

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.SyncStateContract.Columns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import coil.load
import com.pop_flake.R
import com.pop_flake.adapter.SliderMovieAdapter
import com.pop_flake.databinding.FragmentMovieDetailBinding
import com.pop_flake.model.MovieModel
import com.pop_flake.response.MovieDetailResponse
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MovieDetailFragment : Fragment() {


    private lateinit var binding: FragmentMovieDetailBinding

    val movieDetailViewModel: MovieDetailViewModel by viewModels()

    private lateinit var movieDetails: MovieModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        initArgument()
        fetchMovieDetail()

    }

    private fun initArgument() {
        movieDetails = requireArguments().getSerializable("Movie To Details") as MovieModel
    }

    @SuppressLint("SetTextI18n")
    private fun fetchMovieDetail() {
        movieDetailViewModel.fetchMovieToDetails(movieDetails.id.toString())
            .observe(viewLifecycleOwner) { movieDetailResponse ->
                binding.imgMovieDetail.load(movieDetailResponse.dataMovieDetailResponse.image_path) {
                    this.crossfade(1000)
                    this.placeholder(R.drawable.no_photo)
                    this.error(R.drawable.watching_movie)
                }

                val movieDetailAdapter =
                    SliderMovieAdapter(movieDetailResponse.dataMovieDetailResponse.pictures.toList())
                binding.imgSliderMovieDetail.sliderAdapter = movieDetailAdapter

                binding.nameMovieDetail.text = movieDetailResponse.dataMovieDetailResponse.name
                binding.descriptionMovieDetail.text =
                    movieDetailResponse.dataMovieDetailResponse.description
                binding.runtimeMovieDetail.text =
                    "${movieDetailResponse.dataMovieDetailResponse.runtime} Min"
                binding.starMovieDetail.text = movieDetailResponse.dataMovieDetailResponse.rating
                binding.tvMovieDetail.text = movieDetailResponse.dataMovieDetailResponse.network

                binding.btnWebsite.setOnClickListener {
                    clickToWebsite(movieDetailResponse)
                }

                binding.btnEpisodes.setOnClickListener {
                    clickToEpisode(movieDetailResponse)
                }
            }
    }

    private fun clickToWebsite(movieDetailResponse: MovieDetailResponse?) {
        val websiteUrl = Intent(Intent.ACTION_VIEW)
        websiteUrl.data = Uri.parse(movieDetailResponse?.dataMovieDetailResponse?.url)
        requireActivity().startActivity(websiteUrl)
    }

    private fun clickToEpisode(movieDetailResponse: MovieDetailResponse?) {
    }
}