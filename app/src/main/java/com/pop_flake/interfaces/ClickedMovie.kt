package com.pop_flake.interfaces

import com.pop_flake.model.MovieModel

interface ClickedMovie {

    fun clickedMovieToDetail(movieModel: MovieModel)
}