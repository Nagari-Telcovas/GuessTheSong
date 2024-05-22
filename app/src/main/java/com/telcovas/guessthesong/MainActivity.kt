package com.telcovas.guessthesong

import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.ComponentActivity
import androidx.appcompat.widget.AppCompatImageView
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.telcovas.guessthesong.model.AnswerAdapter
import com.telcovas.guessthesong.model.Detail
import com.telcovas.guessthesong.model.Quizinfo
import com.telcovas.guessthesong.model.SongsList
import com.telcovas.guessthesong.ui.theme.GuessTheSongTheme
import java.io.File

class MainActivity : ComponentActivity() {

    lateinit var setupAutoMoneyAdapter: AnswerAdapter

    private lateinit var playimg1: AppCompatImageView
    private lateinit var selectAmountList: RecyclerView
    lateinit var mediaPlayer: MediaPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.layout_dashboard)

        playimg1 = findViewById(R.id.playimg1)
        playimg1.setOnClickListener {
          playAudio()
        }
        selectAmountList = findViewById(R.id.selectAmountList)
        selectAmountList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        mediaPlayer = MediaPlayer()

        // and catch block for our media player.
        try {



          /*  val filePath = "songs.json" // Replace with your JSON file path
            val file = File(filePath)

            val jsonString = file.readText()*/

            val jsonString = readFromAsset()
            val user = Gson().fromJson(jsonString, SongsList::class.java)


            setUpAdapterData(user.response.get(0).details)
            Log.e("songurl",":"+user.response.get(0).details.size)
            // on below line we are setting audio


        } catch (e: Exception) {
            Log.e("Exception",":"+e.message)
            // on below line we are handling our exception.
            e.printStackTrace()
        }


    }
    private fun readFromAsset1(): String {
        val file_name = "songs.json"
        val bufferReader = application.assets.open(file_name).bufferedReader()
        val data = bufferReader.use {
            it.readText()
        }
       // Log.d("readFromAsset", data)

        return data;
    }

    private fun readFromAsset(): String {
        val file_name = "quizlist.json"
        val bufferReader = application.assets.open(file_name).bufferedReader()
        val data = bufferReader.use {
            it.readText()
        }
        // Log.d("readFromAsset", data)

        return data;
    }
    fun playAudio()
    {
        var audioUrl = "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3"

        // on below line we are setting audio stream
        // type as stream music on below line.
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC)

        // on below line we are running a try
        // and catch block for our media player.
        try {




            // on below line we are setting audio
            // source as audio url on below line.
            mediaPlayer.setDataSource(audioUrl)

            // on below line we are
            // preparing our media player.
            mediaPlayer.prepare()

            // on below line we are
            // starting our media player.
            mediaPlayer.start()

        } catch (e: Exception) {

            // on below line we are handling our exception.
            e.printStackTrace()
        }

    }

    private fun setUpAdapterData(listData: List<Detail>){


            setupAutoMoneyAdapter = AnswerAdapter(this, listData)
            selectAmountList.adapter = setupAutoMoneyAdapter


    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GuessTheSongTheme {
        Greeting("Android")
    }
}