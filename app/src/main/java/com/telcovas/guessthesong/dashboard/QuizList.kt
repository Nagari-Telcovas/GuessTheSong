package com.telcovas.guessthesong.dashboard

data class QuizList(

    val question_id: String,
    val question: String,
    val correctOption: String,
    val options: ArrayList<String>,
)


