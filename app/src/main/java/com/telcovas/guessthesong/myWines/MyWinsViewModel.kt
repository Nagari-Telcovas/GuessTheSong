package com.telcovas.guessthesong.myWines

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.telcovas.guessthesong.apicall.ApiHelper
import com.telcovas.guessthesong.apicall.UiState
import com.telcovas.guessthesong.dashboard.QuizList
import kotlinx.coroutines.launch

class MyWinsViewModel(private val apiHelper: ApiHelper) : ViewModel() {

    private val uiState = MutableLiveData<UiState<MyWinsOutput>>()

    init {
        fetchLeaders("userPoints", "9704267248")
    }

    private fun fetchLeaders(reporrtType:String, msisdn: String) {
        viewModelScope.launch {
            uiState.postValue(UiState.Loading)
            try {
                val usersFromApi = apiHelper.getuserPoints(reporrtType, msisdn)

                uiState.postValue(UiState.Success(usersFromApi))
            } catch (e: Exception) {
                uiState.postValue(UiState.Error(e.toString()))
            }
        }
    }

    fun getUiState(): LiveData<UiState<MyWinsOutput>> {
        return uiState
    }

}