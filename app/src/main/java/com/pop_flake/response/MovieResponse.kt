package com.pop_flake.response

import com.google.gson.annotations.SerializedName
import com.pop_flake.model.MovieModel

data class MovieResponse(
    @SerializedName("page")
    val page: Int,
    @SerializedName("pages")
    val pages: Int,
    @SerializedName("total")
    val total: String,
    @SerializedName("tv_shows")
    val movieModel: List<MovieModel>
)