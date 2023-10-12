package com.pop_flake.details

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pop_flake.repository.MovieDetailRepository
import com.pop_flake.response.MovieDetailResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val movieDetailRepository: MovieDetailRepository
) : ViewModel() {


    private val movieDetail: MutableLiveData<MovieDetailResponse> = MutableLiveData()
    private val errorMovieDetail: MutableLiveData<String> = MutableLiveData()



    fun fetchMovieToDetails(movieID: String): LiveData<MovieDetailResponse> {
        viewModelScope.launch {
            val response = movieDetailRepository.fetchMovieToDetails(movieID)
            if (response.isSuccessful) {
                movieDetail.value = response.body()
            } else {
                errorMovieDetail.value = response.errorBody().toString()
            }
        }
        return movieDetail
    }

}