package com.pop_flake.response

import com.pop_flake.model.EpisodeModel

data class DataMovieDetailResponse(
    val id: Int,
    val description: String,
    val episodeModels: List<EpisodeModel>,
//    val genres: List<String>,
    val image_path: String,
    val image_thumbnail_path: String,
    val name: String,
    val network: String,
    val pictures: List<String>,
    val rating: String,
    val runtime: Int,
    val url: String
)