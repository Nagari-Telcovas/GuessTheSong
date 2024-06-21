package com.telcovas.guessthesong.apicall

import com.telcovas.guessthesong.dashboard.QuizList
import com.telcovas.guessthesong.leaderBoard.LeaderBoardList

class ApiHelperImpl(private val apiService: ApiService) : ApiHelper {

    override suspend fun getSongQuestionList(reportType: String): List<QuizList> = apiService.getSongQuestionList(reportType)

    override suspend fun getLeaderBoardList(reportType: String): List<LeaderBoardList> = apiService.getLeaderBoardList(reportType)
    override suspend fun getTotalPlayers(reportType: String): List<QuizList> = apiService.getTotalPlayers(reportType)
    override suspend fun getuserPoints(reportType: String,msisdn:String): List<QuizList> = apiService.getuserPoints(reportType,msisdn)


}