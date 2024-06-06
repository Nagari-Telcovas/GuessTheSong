package com.telcovas.guessthesong.login

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.telcovas.guessthesong.R
import com.telcovas.guessthesong.quizMenu.QuizMenuActivity

class LoginActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        val iLogin = Intent(this, QuizMenuActivity::class.java)
        startActivity(iLogin)
        finish()
    }
}