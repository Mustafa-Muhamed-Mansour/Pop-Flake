package com.pop_flake.repository

import com.pop_flake.network.remote.MovieService
import com.pop_flake.response.MovieDetailResponse
import retrofit2.Response
import javax.inject.Inject

class MovieDetailRepository @Inject constructor(
    private val movieService: MovieService
) {


    suspend fun fetchMovieToDetails(movieName: String): Response<MovieDetailResponse> {
        return movieService.getShowDetails(movieName)
    }

}