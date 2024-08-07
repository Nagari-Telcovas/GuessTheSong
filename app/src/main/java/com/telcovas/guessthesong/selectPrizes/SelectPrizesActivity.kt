package com.telcovas.guessthesong.selectPrizes

import android.content.Intent
import androidx.core.content.ContextCompat
import com.telcovas.guessthesong.BaseActivity
import com.telcovas.guessthesong.CommonMethods
import com.telcovas.guessthesong.R
import com.telcovas.guessthesong.dashboard.MainActivity
import com.telcovas.guessthesong.databinding.ActivitySelectPrizesBinding
import com.telcovas.guessthesong.utils.Constants.Companion.selectedPrizeOption

class SelectPrizesActivity : BaseActivity<ActivitySelectPrizesBinding>(ActivitySelectPrizesBinding::inflate, R.string.app_name) {

    var selectedOption = ""

    override fun initialization(bindingScreen: ActivitySelectPrizesBinding) {
        bindingScreen.forDataText.setOnClickListener {
            selectedOption = bindingScreen.forDataText.text.toString()
            selectedPrizeOption="Data"
            bindingScreen.forDataText.background = ContextCompat.getDrawable(this, R.drawable.shape_select_edit)
            bindingScreen.forMobileText.background = ContextCompat.getDrawable(this, R.drawable.shape_login_edit)
            bindingScreen.forHeadsetText.background = ContextCompat.getDrawable(this, R.drawable.shape_login_edit)
        }
        bindingScreen.forMobileText.setOnClickListener {
            selectedPrizeOption="Mobile"

            selectedOption = bindingScreen.forMobileText.text.toString()
            bindingScreen.forMobileText.background = ContextCompat.getDrawable(this, R.drawable.shape_select_edit)
            bindingScreen.forDataText.background = ContextCompat.getDrawable(this, R.drawable.shape_login_edit)
            bindingScreen.forHeadsetText.background = ContextCompat.getDrawable(this, R.drawable.shape_login_edit)
        }
        bindingScreen.forHeadsetText.setOnClickListener {
            selectedPrizeOption="Headset"

            selectedOption = bindingScreen.forHeadsetText.text.toString()
            bindingScreen.forHeadsetText.background = ContextCompat.getDrawable(this, R.drawable.shape_select_edit)
            bindingScreen.forMobileText.background = ContextCompat.getDrawable(this, R.drawable.shape_login_edit)
            bindingScreen.forDataText.background = ContextCompat.getDrawable(this, R.drawable.shape_login_edit)
        }
        bindingScreen.submitButton.setOnClickListener {
           // selectedPrizeOption=selectedOption
            if(selectedOption.isEmpty())
            {
                CommonMethods.showMessage(this, "Please select one option")

            }
            else {
                CommonMethods.showMessage(this, selectedOption)
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }
    }
}