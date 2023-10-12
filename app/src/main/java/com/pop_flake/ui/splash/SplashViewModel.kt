package com.pop_flake.ui.splash

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SplashViewModel @Inject constructor(

) : ViewModel() {


    val boolean: MutableLiveData<Boolean> = MutableLiveData()


    fun splashScreen(): LiveData<Boolean> {
        viewModelScope.launch {
            delay(4000)
            boolean.value = true
        }
        return boolean
    }


}