package com.telcovas.guessthesong.apicall

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.telcovas.guessthesong.dashboard.MainViewModel
import com.telcovas.guessthesong.leaderBoard.LeaderBoardViewModel
import com.telcovas.guessthesong.login.LoginViewModel
import com.telcovas.guessthesong.myWines.MyWinsViewModel

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
        if (modelClass.isAssignableFrom(LoginViewModel::class.java)) {
            return LoginViewModel(apiHelper) as T
        }
        if (modelClass.isAssignableFrom(MyWinsViewModel::class.java)) {
            return MyWinsViewModel(apiHelper) as T
        }

        throw IllegalArgumentException("Unknown class name")
    }

}