package com.telcovas.guessthesong.apicall

import com.telcovas.guessthesong.dashboard.InsertQuizResponse
import com.telcovas.guessthesong.dashboard.QuizList
import com.telcovas.guessthesong.leaderBoard.LeaderBoardOutput
import com.telcovas.guessthesong.login.LoginResponse
import com.telcovas.guessthesong.myWines.MyWinsOutput

class ApiHelperImpl(private val apiService: ApiService) : ApiHelper {

    override suspend fun getSongQuestionList(reportType: String): List<QuizList> = apiService.getSongQuestionList(reportType)

    override suspend fun getLeaderBoardList(reportType: String): List<LeaderBoardOutput> = apiService.getLeaderBoardList(reportType)
    override suspend fun getTotalPlayers(reportType: String): List<QuizList> = apiService.getTotalPlayers(reportType)
    override suspend fun getuserPoints(reportType: String,msisdn:String): MyWinsOutput = apiService.getuserPoints(reportType,msisdn)
    override suspend fun getuserLogin(
        reportType: String,
        msisdn: String,
        password: String
    ): LoginResponse = apiService.getLogin(reportType,msisdn,password)

    override suspend fun insertQuizDetails(
        reportType: String,
        user_id:String,question_id:String,
        selected_option:String,total_points:String,status:String,
        type: String
    ): InsertQuizResponse = apiService.insertQuizDetails(reportType,user_id,question_id,selected_option,total_points,status,type)


}