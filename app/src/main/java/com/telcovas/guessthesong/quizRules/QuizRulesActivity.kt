package com.telcovas.guessthesong.quizRules


import android.content.Intent
import com.telcovas.guessthesong.BaseActivity
import com.telcovas.guessthesong.R
import com.telcovas.guessthesong.dashboard.MainActivity
import com.telcovas.guessthesong.databinding.ActivityQuizRulesBinding
import com.telcovas.guessthesong.selectPrizes.SelectPrizesActivity

class QuizRulesActivity : BaseActivity<ActivityQuizRulesBinding>(ActivityQuizRulesBinding::inflate, R.string.quizRule) {

    override fun initialization(bindingScreen: ActivityQuizRulesBinding) {
        bindingScreen.continueQuiz.setOnClickListener {
            startActivity(Intent(this, SelectPrizesActivity::class.java))
            finish()
        }
    }
}