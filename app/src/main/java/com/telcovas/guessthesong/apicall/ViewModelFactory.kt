package com.telcovas.guessthesong.apicall

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.telcovas.guessthesong.dashboard.MainViewModel
import com.telcovas.guessthesong.leaderBoard.LeaderBoardViewModel

class ViewModelFactory(private val apiHelper: ApiHelper) :
    ViewModelProvider.NewInstanceFactory() {

    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(MainViewModel::class.java)) {
            return MainViewModel(apiHelper) as T
        }
        if (modelClass.isAssignableFrom(LeaderBoardViewModel::class.java)) {
            return LeaderBoardViewModel(apiHelper) as T
        }

        throw IllegalArgumentException("Unknown class name")
    }

}