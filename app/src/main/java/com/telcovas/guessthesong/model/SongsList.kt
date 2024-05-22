package com.telcovas.guessthesong.model

data class SongsList( val status: String,
                      val message: String,
                      val response: List<Quizinfo>
                     )

data class Quizinfo(
    val songurl: String,
    val details: List<Detail>
)

data class Detail(
    val option1: String,
    val option2: String,
    val option3: String,
    val option4: String
)
