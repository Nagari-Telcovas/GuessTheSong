package com.telcovas.guessthesong.dashboard

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.telcovas.guessthesong.apicall.ApiHelper
import com.telcovas.guessthesong.apicall.UiState
import kotlinx.coroutines.launch

class MainViewModel(private val apiHelper: ApiHelper
) : ViewModel() {

    private val uiState = MutableLiveData<UiState<List<QuizList>>>()

    init {
        fetchUsers("questionList")
    }

    private fun fetchUsers(reporrtType:String) {
        viewModelScope.launch {
            uiState.postValue(UiState.Loading)
            try {
                val usersFromApi = apiHelper.getSongQuestionList(reporrtType)

                uiState.postValue(UiState.Success(usersFromApi))
            } catch (e: Exception) {
                uiState.postValue(UiState.Error(e.toString()))
            }
        }
    }

    fun getUiState(): LiveData<UiState<List<QuizList>>> {
        return uiState
    }

}