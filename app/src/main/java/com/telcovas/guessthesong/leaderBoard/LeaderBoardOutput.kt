package com.telcovas.guessthesong.leaderBoard
//class LeaderBoardList : ArrayList<LeaderBoardListItem>()
data class LeaderBoardOutput(
    val NumberOfquestion: Int,
    val correct_answers: Int,
    val inserted_date: String,
    val total_points: Int,
    val userId: String,
    val user_id: Int
)
