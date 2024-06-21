package com.telcovas.guessthesong.leaderBoard


import android.util.Log
import android.widget.Toast
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.telcovas.guessthesong.BaseActivity
import com.telcovas.guessthesong.R
import com.telcovas.guessthesong.apicall.ApiHelperImpl
import com.telcovas.guessthesong.apicall.RetrofitBuilder
import com.telcovas.guessthesong.apicall.UiState
import com.telcovas.guessthesong.apicall.ViewModelFactory
import com.telcovas.guessthesong.dashboard.MainViewModel
import com.telcovas.guessthesong.databinding.ActivityLeaderBoardBinding

class LeaderBoardActivity : BaseActivity<ActivityLeaderBoardBinding>(ActivityLeaderBoardBinding::inflate, R.string.leaderBoard) {

    private val movieList = ArrayList<LeaderBoardModel>()
    private lateinit var localAdapter: LeaderBoardAdapter
    private lateinit var selectScoreList: RecyclerView
    private lateinit var viewModel: LeaderBoardViewModel
    override fun initialization(bindingScreen: ActivityLeaderBoardBinding) {
        selectScoreList = bindingScreen.selectScoreList
        bindingScreen.toolbarLayout.toolbarBack.setOnClickListener {
            finish()
        }
        bindingScreen.toolbarLayout.toolbarmenu.setOnClickListener {
            finish()
        }
   //     prepareMovieData()
        setupViewModel()
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

    private fun setupViewModel() {
        viewModel = ViewModelProvider(this, ViewModelFactory(ApiHelperImpl(RetrofitBuilder.apiService)))[LeaderBoardViewModel::class.java]

        viewModel.getUiState().observe(this) {
            when (it) {
                is UiState.Success -> {
                    var leaderBoardList = it as ArrayList<LeaderBoardList>
                    Log.e("success",":"+leaderBoardList)
                    localAdapter = LeaderBoardAdapter(this, leaderBoardList)
                    val packsLayoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
                    selectScoreList.layoutManager = packsLayoutManager
                    selectScoreList.itemAnimator = DefaultItemAnimator()
                    selectScoreList.adapter = localAdapter
                    Log.e("success",":"+it.data)
                    // progressBar.visibility = View.GONE
                    // renderList(it.data)
                }
                is UiState.Loading -> {
                    Log.e("Loading",":1")
                    //   progressBar.visibility = View.VISIBLE

                }
                is UiState.Error -> {
                    //Handle Error
                    // progressBar.visibility = View.GONE
                    Log.e("Error",":roor"+it.message)
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }
}