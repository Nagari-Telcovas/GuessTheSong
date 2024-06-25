package com.telcovas.guessthesong.apicall

import com.telcovas.guessthesong.dashboard.InsertQuizResponse
import com.telcovas.guessthesong.dashboard.QuizList
import com.telcovas.guessthesong.leaderBoard.LeaderBoardOutput
import com.telcovas.guessthesong.login.LoginResponse
import com.telcovas.guessthesong.myWines.MyWinsOutput
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("SongQuestionList?")
    suspend fun getSongQuestionList(@Query("reportType") reportType: String): List<QuizList>

    @GET("LoginServlet?")
    suspend fun getLeaderBoardList(@Query("reportType") reportType: String): LeaderBoardOutput

    @GET("LoginServlet?")
    suspend fun getuserPoints(@Query("reportType") reportType: String,@Query("msisdn") msisdn: String): MyWinsOutput

    @GET("LoginServlet?")
    suspend fun getTotalPlayers(@Query("reportType") reportType: String): List<QuizList>

    @GET("LoginServlet?")
    suspend fun getLogin(@Query("reportType") reportType: String,@Query("msisdn") msisdn: String,@Query("password") password: String): LoginResponse
    @GET("SongQuestionList?")
    suspend fun insertQuizDetails(@Query("reportType") reportType: String,
                                  @Query("user_id") user_id: String,
                                  @Query("question_id") question_id: String
                                  ,@Query("selected_option") selected_option: String,
                                  @Query("total_points") total_points: String
                                  ,@Query("status") status: String,
                                  @Query("type") type: String): InsertQuizResponse




/*
https://globicall.globicallservices.com/QuizIntegration/LoginServlet?reportType=userLogin&msisdn=${encodeURIComponent(msisdn)} LOGIN
    1..https://globicall.globicallservices.com/SongsQuizApp/SongQuestionList?reportType=questionList                     (QUESTIONS LIST)

    2...https://globicall.globicallservices.com/SongsQuizApp/SongQuestionList?reportType=InsertingUserDetails&
    user_id=9704267248&question_id=1&selected_option=titanic&total_points=100&status=processing&type=1MB

    3..https://globicall.globicallservices.com/SongsQuizApp/SongQuestionList?reportType=LeaderBoard                     (LEADER BOARD)

    4..https://globicall.globicallservices.com/SongsQuizApp/LoginServlet?reportType=userPoints&msisdn=9704267248         (USER POINTS)

    5..https://globicall.globicallservices.com/SongsQuizApp/LoginServlet?reportType=getTotalPlayers                       (TOTAL PLAYERS)*/
}