package com.telcovas.guessthesong.apicall

import com.telcovas.guessthesong.dashboard.InsertQuizResponse
import com.telcovas.guessthesong.dashboard.QuizList
import com.telcovas.guessthesong.leaderBoard.LeaderBoardOutput
import com.telcovas.guessthesong.login.LoginResponse
import com.telcovas.guessthesong.myWines.MyWinsOutput


interface ApiHelper {

    suspend fun getSongQuestionList(reportType:String): List<QuizList>
    suspend fun getLeaderBoardList(reportType:String): List<LeaderBoardOutput>
    suspend fun getTotalPlayers(reportType:String): List<QuizList>

    suspend fun getuserPoints(reportType:String,msisdn:String): MyWinsOutput

    suspend fun getuserLogin(reportType:String,msisdn:String,password:String): LoginResponse

    suspend fun insertQuizDetails(reportType:String,user_id:String,question_id:String,
                                  selected_option:String,total_points:String,status:String,
                                  type: String
    ): InsertQuizResponse

}