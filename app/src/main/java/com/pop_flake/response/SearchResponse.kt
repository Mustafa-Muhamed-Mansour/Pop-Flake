package com.pop_flake.response

import com.google.gson.annotations.SerializedName
import com.pop_flake.model.MovieModel

data class SearchResponse(
    @SerializedName("tv_shows")
    val movieModel: List<MovieModel>
)