package com.telcovas.guessthesong.apicall

import io.reactivex.Observable
import com.telcovas.guessthesong.dashboard.QuizList
import com.telcovas.guessthesong.leaderBoard.LeaderBoardList
import com.telcovas.guessthesong.login.LoginResponse


interface ApiHelper {

    suspend fun getSongQuestionList(reportType:String): List<QuizList>
    suspend fun getLeaderBoardList(reportType:String): List<LeaderBoardList>
    suspend fun getTotalPlayers(reportType:String): List<QuizList>

    suspend fun getuserPoints(reportType:String,msisdn:String): List<QuizList>

    suspend fun getuserLogin(reportType:String,msisdn:String,password:String): LoginResponse
}