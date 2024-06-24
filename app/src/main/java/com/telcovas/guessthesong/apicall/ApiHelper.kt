package com.telcovas.guessthesong.apicall

import com.telcovas.guessthesong.dashboard.QuizList
import com.telcovas.guessthesong.leaderBoard.LeaderBoardOutput
import com.telcovas.guessthesong.login.LoginResponse


interface ApiHelper {

    suspend fun getSongQuestionList(reportType:String): List<QuizList>
    suspend fun getLeaderBoardList(reportType:String): List<LeaderBoardOutput>
    suspend fun getTotalPlayers(reportType:String): List<QuizList>

    suspend fun getuserPoints(reportType:String,msisdn:String): QuizList

    suspend fun getuserLogin(reportType:String,msisdn:String,password:String): LoginResponse
}