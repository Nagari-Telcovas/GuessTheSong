package com.telcovas.guessthesong.quizMenu


import android.content.Intent
import com.telcovas.guessthesong.BaseActivity
import com.telcovas.guessthesong.R
import com.telcovas.guessthesong.databinding.ActivityQuizMenuBinding
import com.telcovas.guessthesong.leaderBoard.LeaderBoardActivity
import com.telcovas.guessthesong.myWines.MyWinsActivity
import com.telcovas.guessthesong.quizRules.QuizRulesActivity

class QuizMenuActivity : BaseActivity<ActivityQuizMenuBinding>(ActivityQuizMenuBinding::inflate, R.string.app_name) {

    override fun initialization(bindingScreen: ActivityQuizMenuBinding) {
        bindingScreen.quizes.setOnClickListener {
            var inItem = Intent(this, QuizRulesActivity::class.java)
            startActivity(inItem)
            finish()
        }

        bindingScreen.wines.setOnClickListener {
            var inItem = Intent(this, MyWinsActivity::class.java)
            startActivity(inItem)
        }

        bindingScreen.leaderboard.setOnClickListener {
            var inItem = Intent(this, LeaderBoardActivity::class.java)
            startActivity(inItem)
        }
    }

}