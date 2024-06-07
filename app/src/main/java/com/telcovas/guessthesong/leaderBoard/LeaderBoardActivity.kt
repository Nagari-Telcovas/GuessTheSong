package com.telcovas.guessthesong.leaderBoard

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.telcovas.guessthesong.BaseActivity
import com.telcovas.guessthesong.R
import com.telcovas.guessthesong.databinding.ActivityLeaderBoardBinding

class LeaderBoardActivity : BaseActivity<ActivityLeaderBoardBinding>(ActivityLeaderBoardBinding::inflate, R.string.leaderBoard) {

    private val movieList = ArrayList<LeaderBoardModel>()
    private lateinit var localAdapter: LeaderBoardAdapter
    private lateinit var selectScoreList: RecyclerView

    override fun initialization(bindingScreen: ActivityLeaderBoardBinding) {
        selectScoreList = bindingScreen.selectScoreList
        bindingScreen.toolbarLayout.toolbarBack.setOnClickListener {
            finish()
        }
        localAdapter = LeaderBoardAdapter(this, movieList)
        val packsLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        selectScoreList.layoutManager = packsLayoutManager
        selectScoreList.itemAnimator = DefaultItemAnimator()
        selectScoreList.adapter = localAdapter
        prepareMovieData()
    }

    private fun prepareMovieData() {
        var movie = LeaderBoardModel("1", "st", "Antonio Conte 01", 500)
        movieList.add(movie)
        movie = LeaderBoardModel("2", "nd", "Antonio Conte 02",  490)
        movieList.add(movie)
        movie = LeaderBoardModel("3", "rd", "Antonio Conte 03", 480)
        movieList.add(movie)
        movie = LeaderBoardModel("4", "th", "Antonio Conte 04" , 470)
        movieList.add(movie)
        movie = LeaderBoardModel("5", "th", "Antonio Conte 05", 460)
        movieList.add(movie)
        movie = LeaderBoardModel("6", "th", "Antonio Conte 06", 500)
        movieList.add(movie)
        movie = LeaderBoardModel("7", "th", "Antonio Conte 07", 450)
        movieList.add(movie)
        movie = LeaderBoardModel("8", "th", "Antonio Conte 08", 490)
        movieList.add(movie)
        localAdapter.notifyDataSetChanged()
    }
}