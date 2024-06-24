package com.telcovas.guessthesong.dashboard

data class QuizList(
    val userId: String,
    val maxPoint: String,
    val userList: List<UserQuizList>)

data class UserQuizList(
    val NumberOfquestion: Int,
    val correct_answers: Int,
    val inserted_date: String,
    val total_points: Int,
    val user_id: Int)

/*data class QuizList(

    val question_id: String,
    val question: String,
    val correctOption: String,
    val options: ArrayList<String>,
)*/


