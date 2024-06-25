package com.telcovas.guessthesong.login


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.telcovas.guessthesong.apicall.ApiHelper
import com.telcovas.guessthesong.apicall.UiState
import com.telcovas.guessthesong.utils.Constants
import kotlinx.coroutines.launch

class LoginViewModel(private val apiHelper: ApiHelper) : ViewModel() {

    private val uiState = MutableLiveData<UiState<LoginResponse>>()

     fun fetchLogin(reportType:String, msisdn: String, password: String) {
         Constants.Loggedinmsisdn=msisdn.trim()
         Log.e("fetchLogin","::"+msisdn.trim().length)
         Log.e("fetchLogin","::"+msisdn.trim())

         viewModelScope.launch {
            uiState.postValue(UiState.Loading)
            try {
                val usersFromApi = apiHelper.getuserLogin(reportType, msisdn.trim(), password)

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