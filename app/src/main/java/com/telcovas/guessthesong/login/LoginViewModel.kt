package com.telcovas.guessthesong.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.telcovas.guessthesong.apicall.ApiHelper
import com.telcovas.guessthesong.apicall.UiState
import kotlinx.coroutines.launch

class LoginViewModel(private val apiHelper: ApiHelper
) : ViewModel() {


    private val uiState = MutableLiveData<UiState<LoginResponse>>()
    init {
      //  fetchLogin("userLogin","9032364590","telcovas")
    }

     fun fetchLogin(reporrtType:String,msisdn: String,
                           password: String) {
        viewModelScope.launch {
            uiState.postValue(UiState.Loading)
            try {
                val usersFromApi = apiHelper.getuserLogin(reporrtType,msisdn,password)

                uiState.postValue(UiState.Success(usersFromApi))
            } catch (e: Exception) {
                uiState.postValue(UiState.Error(e.toString()))
            }
        }
    }

    fun getUiState(): LiveData<UiState<LoginResponse>>{
        return uiState
    }

}