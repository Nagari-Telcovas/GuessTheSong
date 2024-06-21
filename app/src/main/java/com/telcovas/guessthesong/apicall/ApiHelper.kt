package com.telcovas.guessthesong.apicall

import com.telcovas.guessthesong.dashboard.QuizList
import com.telcovas.guessthesong.leaderBoard.LeaderBoardList


interface ApiHelper {

    suspend fun getSongQuestionList(reportType:String): List<QuizList>
    suspend fun getLeaderBoardList(reportType:String): List<LeaderBoardList>
    suspend fun getTotalPlayers(reportType:String): List<QuizList>

    suspend fun getuserPoints(reportType:String,msisdn:String): List<QuizList>


}