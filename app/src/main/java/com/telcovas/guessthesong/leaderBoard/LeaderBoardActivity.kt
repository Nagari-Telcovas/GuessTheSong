package com.telcovas.guessthesong.leaderBoard


import android.util.Log
import android.view.View
import android.widget.ProgressBar
import androidx.appcompat.widget.AppCompatTextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.telcovas.guessthesong.BaseActivity
import com.telcovas.guessthesong.CommonMethods
import com.telcovas.guessthesong.R
import com.telcovas.guessthesong.apicall.ApiHelperImpl
import com.telcovas.guessthesong.apicall.RetrofitBuilder
import com.telcovas.guessthesong.apicall.UiState
import com.telcovas.guessthesong.apicall.ViewModelFactory
import com.telcovas.guessthesong.databinding.ActivityLeaderBoardBinding

class LeaderBoardActivity : BaseActivity<ActivityLeaderBoardBinding>(ActivityLeaderBoardBinding::inflate, R.string.leaderBoard) {

    private val movieList = ArrayList<LeaderBoardModel>()
    private lateinit var localAdapter: LeaderBoardAdapter
    private lateinit var selectScoreList: RecyclerView
    private lateinit var viewModel: LeaderBoardViewModel
    private lateinit var totalPlays: AppCompatTextView
    private lateinit var totalRank: AppCompatTextView
    private lateinit var leaderProgress: ProgressBar

    override fun initialization(bindingScreen: ActivityLeaderBoardBinding) {
        selectScoreList = bindingScreen.selectScoreList
        totalPlays = bindingScreen.totalPlays
        totalRank = bindingScreen.totalRank
        leaderProgress = bindingScreen.leaderProgress.progressView
        bindingScreen.toolbarLayout.toolbarBack.setOnClickListener {
            finish()
        }
        bindingScreen.toolbarLayout.toolbarmenu.setOnClickListener {
            finish()
        }
       // leaderProgress.visibility = View.VISIBLE
        setupViewModel()
    }

    private fun setupViewModel() {

        viewModel = ViewModelProvider(this, ViewModelFactory(ApiHelperImpl(RetrofitBuilder.apiService)))[LeaderBoardViewModel::class.java]

        viewModel.getUiState().observe(this) {
            when (it) {
                is UiState.Success -> {
                   var leaderBoardList = it.data.getUserData as ArrayList<LeaderBoardItem>
                    totalPlays.text = it.data.totalPlayers
                    if (it.data.rank.isNotEmpty())
                        totalRank.text = it.data.rank
                    else
                        totalRank.text = "0"
                    localAdapter = LeaderBoardAdapter(this, leaderBoardList)
                    selectScoreList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                    selectScoreList.itemAnimator = DefaultItemAnimator()
                    selectScoreList.adapter = localAdapter
                    leaderProgress.visibility = View.GONE
                }
                is UiState.Loading -> {
                    Log.e("Loading",":1")
                    leaderProgress.visibility = View.VISIBLE

                }
                is UiState.Error -> {
                    CommonMethods.showMessage(this, it.message)
                    Log.e("Error",":roor"+it.message)
                }
            }
        }
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