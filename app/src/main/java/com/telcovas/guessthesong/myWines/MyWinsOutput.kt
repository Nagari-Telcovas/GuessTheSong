package com.telcovas.guessthesong.myWines

data class MyWinsOutput(
    val userId: String,
    val maxPoint: String,
    val totalPlayers: String,
    val userList: List<UserQuizList>)

data class UserQuizList(
    val NumberOfquestion: Int,
    val correct_answers: Int,
    val inserted_date: String,
    val total_points: Int,
    val user_id: Int)