package com.telcovas.guessthesong

import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.telcovas.guessthesong.model.AnswerAdapter
import com.telcovas.guessthesong.model.Detail
import com.telcovas.guessthesong.model.SongsList
import com.telcovas.guessthesong.ui.theme.GuessTheSongTheme
import de.hdodenhof.circleimageview.CircleImageView

class MainActivity : ComponentActivity() {

    lateinit var setupAutoMoneyAdapter: AnswerAdapter

    private lateinit var playimg1: CircleImageView
    private lateinit var selectAmountList: RecyclerView
    lateinit var mediaPlayer: MediaPlayer

    lateinit var user:SongsList
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        playimg1 = findViewById(R.id.playSong)
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
             user = Gson().fromJson(jsonString, SongsList::class.java)


            setUpAdapterData(user.response.get(0).details)
            Log.e("songurl",":"+user.response.get(0).details.size)
            // on below line we are setting audio


        } catch (e: Exception) {
            Log.e("Exception",":"+e.message)
            // on below line we are handling our exception.
            e.printStackTrace()
        }


    }
    private fun readFromAsset(): String {
        val file_name = "songs.json"
        val bufferReader = application.assets.open(file_name).bufferedReader()
        val data = bufferReader.use {
            it.readText()
        }
       // Log.d("readFromAsset", data)

        return data;
    }

    private fun readFromAsset1(): String {
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

        var audioUrl =  user.response.get(0).songurl
       // var audioUrl = "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3"

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
        text = stringResource(id = R.string.app_name),
        modifier = modifier.fillMaxSize(),
        color = colorResource(id = R.color.body_bg),
        fontFamily = (FontFamily(Font(R.font.kavoon))),
        fontSize = 18.sp
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GuessTheSongTheme {
        Greeting("Android")
    }
}