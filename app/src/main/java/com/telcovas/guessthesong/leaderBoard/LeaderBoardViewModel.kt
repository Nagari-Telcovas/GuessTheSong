package com.telcovas.guessthesong.leaderBoard

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.telcovas.guessthesong.apicall.ApiHelper
import com.telcovas.guessthesong.apicall.UiState
import com.telcovas.guessthesong.utils.Constants
import kotlinx.coroutines.launch

class LeaderBoardViewModel(private val apiHelper: ApiHelper
) : ViewModel() {

    private val uiState = MutableLiveData<UiState<LeaderBoardOutput>>()

    init {
        fetchLeaders("LeaderBoard",Constants.Loggedinmsisdn)
    }

    private fun fetchLeaders(reporrtType:String,msisdn:String) {
        viewModelScope.launch {
            uiState.postValue(UiState.Loading)
            try {
                val usersFromApi = apiHelper.getLeaderBoardList(reporrtType,msisdn)

                uiState.postValue(UiState.Success(usersFromApi))
            } catch (e: Exception) {
                uiState.postValue(UiState.Error(e.toString()))
            }
        }
    }

    fun getUiState(): LiveData<UiState<LeaderBoardOutput>>{
        return uiState
    }

}