package com.pop_flake.search

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pop_flake.repository.SearchRepository
import com.pop_flake.response.SearchResponse
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val searchRepository: SearchRepository
) : ViewModel() {

    private val search: MutableLiveData<SearchResponse> = MutableLiveData()
    private val error: MutableLiveData<String> = MutableLiveData()


    fun getSearchMovies(searchMovie: String): LiveData<SearchResponse> {
        viewModelScope.launch {
            val response = searchRepository.getSearchMovies(searchMovie)
            if (response.isSuccessful) {
                search.value = response.body()
            } else {
                error.value = response.errorBody().toString()
            }
        }
        return search
    }

}