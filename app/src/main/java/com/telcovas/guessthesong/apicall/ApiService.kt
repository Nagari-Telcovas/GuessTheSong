package com.telcovas.guessthesong.apicall

import com.telcovas.guessthesong.dashboard.QuizList
import com.telcovas.guessthesong.leaderBoard.LeaderBoardList
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ApiService {

    @GET("SongQuestionList?")
    suspend fun getSongQuestionList(@Query("reportType") reportType: String): List<QuizList>

    @GET("SongQuestionList?")
    suspend fun getLeaderBoardList(@Query("reportType") reportType: String): List<LeaderBoardList>

    @GET("LoginServlet?")
    suspend fun getuserPoints(@Query("reportType") reportType: String,@Query("msisdn") msisdn: String): List<QuizList>

    @GET("LoginServlet?")
    suspend fun getTotalPlayers(@Query("reportType") reportType: String): List<QuizList>

/*
    1..https://globicall.globicallservices.com/SongsQuizApp/SongQuestionList?reportType=questionList                     (QUESTIONS LIST)

    2...https://globicall.globicallservices.com/SongsQuizApp/SongQuestionList?reportType=InsertingUserDetails&
    user_id=9704267248&question_id=1&selected_option=titanic&total_points=100&status=processing&type=1MB

    3..https://globicall.globicallservices.com/SongsQuizApp/SongQuestionList?reportType=LeaderBoard                     (LEADER BOARD)

    4..https://globicall.globicallservices.com/SongsQuizApp/LoginServlet?reportType=userPoints&msisdn=9704267248         (USER POINTS)

    5..https://globicall.globicallservices.com/SongsQuizApp/LoginServlet?reportType=getTotalPlayers                       (TOTAL PLAYERS)*/
}