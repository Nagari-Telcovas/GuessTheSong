package com.telcovas.guessthesong.splash

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import com.telcovas.guessthesong.MainActivity
import com.telcovas.guessthesong.R
import com.telcovas.guessthesong.quizMenu.QuizMenuActivity

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        displaySplash()
    }

    private fun displaySplash() {
        Handler(Looper.getMainLooper()).postDelayed({
                val iLogin = Intent(this, QuizMenuActivity::class.java)
                startActivity(iLogin)
                finish()
        }, 2000)
    }
}