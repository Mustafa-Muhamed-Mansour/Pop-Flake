package com.pop_flake.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pop_flake.other.Constant.Companion.currentPage
import com.pop_flake.repository.HomeRepository
import com.pop_flake.response.MovieResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val homeRepository: HomeRepository
) : ViewModel() {


    private val movieHome: MutableLiveData<MovieResponse> = MutableLiveData()
    private val errorHome: MutableLiveData<String> = MutableLiveData()


    fun getMostPopular(page: Int): LiveData<MovieResponse> {
        viewModelScope.launch {
            val response = homeRepository.getMostPopular(page)
            if (response.isSuccessful) {
                movieHome.value = response.body()
            } else {
                errorHome.value = response.errorBody().toString()
            }
        }
        return movieHome
    }
}