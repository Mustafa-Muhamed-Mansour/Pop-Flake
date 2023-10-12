package com.pop_flake.repository

import com.pop_flake.network.remote.MovieService
import com.pop_flake.response.MovieResponse
import retrofit2.Response
import javax.inject.Inject

class HomeRepository @Inject constructor(
    private val movieService: MovieService
) {


    suspend fun getMostPopular(page: Int): Response<MovieResponse> {
        return movieService.getMostPopular(page)
    }

}