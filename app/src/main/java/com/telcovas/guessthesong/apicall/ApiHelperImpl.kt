package com.telcovas.guessthesong.apicall

import io.reactivex.Observable
import com.telcovas.guessthesong.dashboard.QuizList
import com.telcovas.guessthesong.leaderBoard.LeaderBoardList
import com.telcovas.guessthesong.login.LoginResponse

class ApiHelperImpl(private val apiService: ApiService) : ApiHelper {

    override suspend fun getSongQuestionList(reportType: String): List<QuizList> = apiService.getSongQuestionList(reportType)

    override suspend fun getLeaderBoardList(reportType: String): List<LeaderBoardList> = apiService.getLeaderBoardList(reportType)
    override suspend fun getTotalPlayers(reportType: String): List<QuizList> = apiService.getTotalPlayers(reportType)
    override suspend fun getuserPoints(reportType: String,msisdn:String): List<QuizList> = apiService.getuserPoints(reportType,msisdn)
    override suspend fun getuserLogin(
        reportType: String,
        msisdn: String,
        password: String
    ): LoginResponse = apiService.getLogin(reportType,msisdn,password)


}