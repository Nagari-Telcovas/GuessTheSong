package com.telcovas.guessthesong.selectPrizes

import android.content.Intent
import com.telcovas.guessthesong.BaseActivity
import com.telcovas.guessthesong.R
import com.telcovas.guessthesong.dashboard.MainActivity
import com.telcovas.guessthesong.databinding.ActivitySelectPrizesBinding

class SelectPrizesActivity : BaseActivity<ActivitySelectPrizesBinding>(ActivitySelectPrizesBinding::inflate, R.string.app_name) {

    override fun initialization(bindingScreen: ActivitySelectPrizesBinding) {
        bindingScreen.submitButton.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
            finish()
        }
    }
}