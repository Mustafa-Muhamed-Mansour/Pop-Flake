package com.pop_flake.network.remote

import com.pop_flake.other.Constant.Companion.ENDPOINT_MOST_POPULAR
import com.pop_flake.other.Constant.Companion.ENDPOINT_SEARCH
import com.pop_flake.other.Constant.Companion.ENDPOINT_SHOW_DETAILS
import com.pop_flake.response.MovieDetailResponse
import com.pop_flake.response.MovieResponse
import com.pop_flake.response.SearchResponse
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface MovieService {


    @GET(ENDPOINT_MOST_POPULAR)
    suspend fun getMostPopular(@Query("page") page: Int): Response<MovieResponse>

    @GET(ENDPOINT_SHOW_DETAILS)
    suspend fun getShowDetails(@Query("q") movieID: String): Response<MovieDetailResponse>

    @GET(ENDPOINT_SEARCH)
    suspend fun getSearchMovies(@Query("q") searchMovie: String): Response<SearchResponse>

}