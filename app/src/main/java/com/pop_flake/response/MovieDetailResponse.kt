package com.pop_flake.response

import com.google.gson.annotations.SerializedName

data class MovieDetailResponse(
    @SerializedName("tvShow")
    val dataMovieDetailResponse: DataMovieDetailResponse
)