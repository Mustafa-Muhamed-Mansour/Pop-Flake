package com.pop_flake.other

import android.content.Context
import android.widget.Toast

class Validation(val context: Context) {

    fun checkSearchMovieName(searchMovie: String): String {
        if (searchMovie.isNullOrEmpty()) {
            Toast.makeText(context, "Please enter a movie name", Toast.LENGTH_SHORT)
                .show()
        }
        return searchMovie
    }
}