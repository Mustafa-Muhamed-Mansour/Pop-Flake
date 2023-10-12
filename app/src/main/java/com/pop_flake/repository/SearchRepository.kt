package com.pop_flake.repository

import com.pop_flake.network.remote.MovieService
import com.pop_flake.response.SearchResponse
import retrofit2.Response
import javax.inject.Inject

class SearchRepository @Inject constructor(
    private val movieService: MovieService
) {


    suspend fun getSearchMovies(searchMovie: String): Response<SearchResponse> {
        return movieService.getSearchMovies(searchMovie)
    }

}