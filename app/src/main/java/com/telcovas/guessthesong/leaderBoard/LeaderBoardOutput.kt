package com.telcovas.guessthesong.leaderBoard

class LeaderBoardOutput : ArrayList<LeaderBoardItem>()

data class LeaderBoardItem(
    val NumberOfquestion: Int,
    val correct_answers: Int,
    val inserted_date: String,
    val total_points: Int,
    val userId: String,
    val user_id: Int
)
