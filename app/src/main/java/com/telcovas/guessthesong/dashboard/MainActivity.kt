package com.telcovas.guessthesong.dashboard

import android.content.Context
import android.content.Intent
import android.media.AudioManager
import android.media.MediaPlayer
import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.WindowManager
import android.widget.ProgressBar
import android.widget.SeekBar
import android.widget.TextView
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.widget.AppCompatButton
import androidx.appcompat.widget.AppCompatTextView
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.gson.Gson
import com.telcovas.guessthesong.CommonMethods
import com.telcovas.guessthesong.R
import com.telcovas.guessthesong.apicall.ApiHelperImpl
import com.telcovas.guessthesong.apicall.RetrofitBuilder
import com.telcovas.guessthesong.apicall.UiState
import com.telcovas.guessthesong.apicall.ViewModelFactory
import com.telcovas.guessthesong.model.AnswerAdapter
import com.telcovas.guessthesong.model.Detail
import com.telcovas.guessthesong.model.Quizinfo
import com.telcovas.guessthesong.model.SongClickListener
import com.telcovas.guessthesong.model.SongsList
import com.telcovas.guessthesong.myWines.MyWinsActivity
import com.telcovas.guessthesong.purchasePacks.PurchasePacksActivity
import com.telcovas.guessthesong.quizMenu.QuizMenuActivity
import com.telcovas.guessthesong.utils.Constants
import com.telcovas.guessthesong.utils.LoadingUtils
import de.hdodenhof.circleimageview.CircleImageView

class MainActivity : ComponentActivity(), SongClickListener {

    lateinit var setupAutoMoneyAdapter: AnswerAdapter
    lateinit var setupquizAdapter: QuizListAdapter


    private lateinit var pauseSong: CircleImageView
    private lateinit var reloadSong: CircleImageView
    private lateinit var playimg1: CircleImageView
    private lateinit var selectAmountList: RecyclerView
    lateinit var mediaPlayer: MediaPlayer
    lateinit var submitButton: AppCompatButton
    lateinit var user:SongsList
    private lateinit var runnable:Runnable
    private var handler: Handler = Handler()
    private var pause:Boolean = false
    private lateinit var songProgress: SeekBar
    private lateinit var progressbar_id: ProgressBar
    private var qnumber=0
    private lateinit var percentData: AppCompatTextView
    private lateinit var skipButton: AppCompatTextView
    private lateinit var durationTimeText: AppCompatTextView


    lateinit  var timer:CountDownTimer
    var timerStarted:Boolean=false

    private var selectedOptionType: String = "0"
    var correctAnsList = ArrayList<Int>()
    private var isAnswered:Boolean = false

    var lmilliseconds: Long = 60000
    private lateinit var viewModel: MainViewModel

    lateinit  var context:Context

    lateinit var quizlist:List<QuizList>
    var question_status="processing"
    //lateinit  var Loggedinmsisdn:String
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        context=this
        setContentView(R.layout.activity_main)
       // Loggedinmsisdn= CommonMethods.getSharedPreference(this, "countryCode")+""+CommonMethods.getSharedPreference(this, "mobileNumber")

        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        submitButton = findViewById(R.id.submitButton)
        playimg1 = findViewById(R.id.playSong)
        songProgress= findViewById(R.id.songProgress)
        progressbar_id= findViewById(R.id.progressbar_id)
        pauseSong= findViewById(R.id.pauseSong)
        reloadSong= findViewById(R.id.reloadSong)
        percentData= findViewById(R.id.percentData)
        durationTimeText= findViewById(R.id.durationTimeText)
        skipButton= findViewById(R.id.skipButton)
        playimg1.setOnClickListener {
            try {
            if(pause){
                mediaPlayer.seekTo(mediaPlayer.currentPosition)
                mediaPlayer.start()
                pause = false
            }else {
               // playAudio()

                playSound()
            }
            }
            catch (e:Exception)
            {

            }
        }
        pauseSong.setOnClickListener {
            try {
                if (mediaPlayer.isPlaying)
                    mediaPlayer.pause()
                pause = true
            }
            catch (e:Exception)
            {

            }
        }

        reloadSong.setOnClickListener {
          // playAudio()
            playSound()
        }

        skipButton.setOnClickListener {
            // try {


                qnumber++
                if (qnumber > user.response.size)
                    qnumber = user.response.size
                progressbar_id.progress = qnumber
                percentData.text = qnumber.toString() + "/" + user.response.size.toString()
                Log.e("submit11", ":" + qnumber)



                if (qnumber < user.response.size) {
                    pause = false

                 /*   if (mediaPlayer.isPlaying) {

                        mediaPlayer.stop()
                        mediaPlayer.release()
                    }*/

                    setUpAdapterData(user.response)
                } else {
                    if (mediaPlayer.isPlaying) {

                        mediaPlayer.stop()
                        mediaPlayer.release()
                    }
                    //Log.d("Correct Answers", correctAnsList.size)
                    showDialog(this, correctAnsList.size,false)
                }
                selectedOptionType="0"



        }
        submitButton.setOnClickListener {
            pause = false
            stopSound()
            if(timerStarted) {
                timer?.cancel()
                timerStarted=false
            }
            if (selectedOptionType != "0") {
               var ques_id= quizlist.get(qnumber).question_id
                var totalmb=qnumber+1
                var type=totalmb.toString()+"MB"



                if (selectedOptionType ==quizlist[qnumber].correctOption) {
                    question_status="processing"
                    correctAnsList.add(qnumber)
                } else {
                    question_status="completed"
                    showDialog(this, correctAnsList.size,false)

                }
                if(qnumber==0)
                {
                    ques_id = quizlist.get(qnumber).question_id
                    totalmb = qnumber+1
                    type = totalmb.toString() + "MB"
                }else {
                    ques_id = quizlist.get(qnumber - 1).question_id
                    totalmb = qnumber + 1
                    type = totalmb.toString() + "MB"
                }
                Log.e("Loggedinmsisdn", ":" + Constants.Loggedinmsisdn)
                viewModel.updateQuiz("InsertingUserDetails",Constants.Loggedinmsisdn.trim(),ques_id,selectedOptionType,"100",question_status,type)

            } else {
                Toast.makeText(this, "Please select the Answer!!", Toast.LENGTH_SHORT).show()
            }

           // try {


           /* } catch (e:Exception)
            {
                Log.e("Excerrr",":"+e.toString())
            }*/
        }
        selectAmountList = findViewById(R.id.selectAmountList)
        selectAmountList.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)


        try {

        mediaPlayer = MediaPlayer()
        mediaPlayer.setAudioStreamType(AudioManager.STREAM_MUSIC)
        // and catch block for our media player.

          /*  val filePath = "songs.json" // Replace with your JSON file path
            val file = File(filePath)
            val jsonString = file.readText()*/

         //   val jsonString = readFromAsset()
          //   user = Gson().fromJson(jsonString, SongsList::class.java)
          //  setUpAdapterData(user.response)
          //  setUpAdapterData(user.response.get(0).details)
         //   Log.e("songurl",":"+user.response)
            // on below line we are setting audio
          //  progressbar_id.max=user.response.size
          //  percentData.text=qnumber.toString()+"/"+user.response.size.toString()


            setupViewModel()

            onBackPressedDispatcher?.addCallback(this, object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {

                    onBackClickedDialog()
                }
            })
        } catch (e: Exception) {
            Log.e("Exception",":"+e.message)
            // on below line we are handling our exception.
            e.printStackTrace()
        }
    }
    private fun moveToNextSong()
    {

            qnumber++
            if (qnumber > quizlist.size)
                qnumber = quizlist.size
            progressbar_id.progress = qnumber
            percentData.text = qnumber.toString() + "/" + quizlist.size.toString()
            Log.e("submit11", ":" + qnumber)

           /* if (selectedOptionType ==quizlist[qnumber - 1].correctOption) {
                Toast.makeText(this, "Correct Answer", Toast.LENGTH_SHORT).show()
                correctAnsList.add(qnumber)
            } else {
                Toast.makeText(this, "Wrong Answer", Toast.LENGTH_SHORT).show()
            }*/

        Log.e("submit22", ":" + quizlist.size)

            if (qnumber < quizlist.size) {
                pause = false
                stopSound()

              /*  if (mediaPlayer != null ) {
                    if (mediaPlayer.isPlaying) {

                        mediaPlayer.stop()
                        mediaPlayer.release()
                    }
                }*/
                // playAudio()
                // playSound()
                setUpQuizAdapterData(quizlist)
                if(timerStarted) {
                    timer?.cancel()
                    timerStarted=false
                }
                startTimer()
            } else {
                if(timerStarted) {
                    timer?.cancel()
                    timerStarted=false
                }
                if (mediaPlayer.isPlaying) {

                    mediaPlayer.stop()
                    mediaPlayer.release()
                }
                //Log.d("Correct Answers", correctAnsList.size)
                showDialog(this, correctAnsList.size,false)
            }
            selectedOptionType="0"
            //  val intentSubmit = Intent(this, PurchasePacksActivity::class.java)
            // startActivity(intentSubmit)

    }

    private fun moveToNextSonglocal()
    {

        qnumber++
        if (qnumber > user.response.size)
            qnumber = user.response.size
        progressbar_id.progress = qnumber
        percentData.text = qnumber.toString() + "/" + user.response.size.toString()
        Log.e("submit11", ":" + qnumber)

        if (selectedOptionType == user.response[qnumber - 1].answer) {
            Toast.makeText(this, "Correct Answer", Toast.LENGTH_SHORT).show()
            correctAnsList.add(qnumber)
        } else {
            Toast.makeText(this, "Wrong Answer", Toast.LENGTH_SHORT).show()
        }

        if (qnumber < user.response.size) {
            pause = false
            stopSound()

            /*  if (mediaPlayer != null ) {
                  if (mediaPlayer.isPlaying) {

                      mediaPlayer.stop()
                      mediaPlayer.release()
                  }
              }*/
            // playAudio()
            // playSound()
            setUpAdapterData(user.response)
            if(timerStarted) {
                timer?.cancel()
                timerStarted=false
            }
            startTimer()
        } else {
            if(timerStarted) {
                timer?.cancel()
                timerStarted=false
            }
            if (mediaPlayer.isPlaying) {

                mediaPlayer.stop()
                mediaPlayer.release()
            }
            //Log.d("Correct Answers", correctAnsList.size)
            showDialog(this, correctAnsList.size,false)
        }
        selectedOptionType="0"
        //  val intentSubmit = Intent(this, PurchasePacksActivity::class.java)
        // startActivity(intentSubmit)

    }
    private fun startTimer()
    {
        var secs=0
        durationTimeText?.text = secs.toString()
        if(!timerStarted) {
            timer = object : CountDownTimer(lmilliseconds, 1000) {
                override fun onTick(p0: Long) {
                    timerStarted = true

                    secs++

                    durationTimeText?.text = secs.toString()



                }

                override fun onFinish() {

                    if(timerStarted) {
                        timer?.cancel()
                        timerStarted = false
                    }
                    showDialog(context, correctAnsList.size,true)
                   // moveToNextSong()

                }
            }.start()
        }
    }
    private fun readFromAsset(): String {
        val file_name = "songsupdate.json"
        val bufferReader = application.assets.open(file_name).bufferedReader()
        val data = bufferReader.use {
            it.readText()
        }
       // Log.d("readFromAsset", data)
        return data;
    }

    private fun readPackages(): String {
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

        var audioUrl =  user.response.get(qnumber).songurl
        // var audioUrl = "https://www.soundhelix.com/examples/mp3/SoundHelix-Song-1.mp3"

        // on below line we are setting audio stream
        // type as stream music on below line.


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
            initializeSeekBar()
            // Thread().start();


            /*    var duration = mediaPlayer.getDuration();
                Log.e("duration",":"+duration)

                val amoungToupdate = duration / 100

                Log.e("amoungToupdate",":"+amoungToupdate)

                val mTimer = Timer()
                mTimer.schedule(object : TimerTask() {
                    override fun run() {
                        runOnUiThread {
                          //  if (amoungToupdate * songProgress.getProgress() < duration) {
                                var p: Int = songProgress.getProgress()
                                p += 1
                                Log.e("progressoupdate",":"+p)
                                songProgress.setProgress(p)
                           // }
                        }
                    }
                }, amoungToupdate.toLong())
                Thread().start();*/
            //   run()
        } catch (e: Exception) {

            // on below line we are handling our exception.
            e.printStackTrace()
        }


    }

    fun playAudio1()
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
    private fun initializeSeekBar() {
        songProgress.max = mediaPlayer.seconds

        runnable = Runnable {
            songProgress.progress = mediaPlayer.currentSeconds

            val diff = mediaPlayer.seconds - mediaPlayer.currentSeconds


            handler.postDelayed(runnable, 1000)
        }
        handler.postDelayed(runnable, 1000)
    }

    val MediaPlayer.seconds:Int
        get() {
            return this.duration / 1000
        }
    // Creating an extension property to get media player current position in seconds
    val MediaPlayer.currentSeconds:Int
        get() {
            return this.currentPosition/1000
        }

    private fun setUpQuizAdapterData(listData: List<QuizList>){
        var quizListData = listData.get(qnumber).options

        Log.d("setUpQuizAdapterData",":"+qnumber+"::"+quizListData.toString())

     //   var quizListData =listData[qnumber] as ArrayList<Detail>
        //var quizListData22 =quizListData.get(0).option1 as ArrayList<Detail>
     //   Log.d("SongUrl22", quizListData.toString())
        setupquizAdapter = QuizListAdapter(this, quizListData, this)
        selectAmountList.adapter = setupquizAdapter

    }
    private fun setUpAdapterData(listData: List<Quizinfo>){

        var quizListData =listData[qnumber].details as ArrayList<Detail>
        //var quizListData22 =quizListData.get(0).option1 as ArrayList<Detail>
        Log.d("SongUrl22", quizListData.toString())
            setupAutoMoneyAdapter = AnswerAdapter(this, quizListData, this)
            selectAmountList.adapter = setupAutoMoneyAdapter

    }

    private fun showDialog(context: Context, correctAnswer: Int,isFinish:Boolean) {
        val builder = AlertDialog.Builder(this).create()
        val view = layoutInflater.inflate(R.layout.dialog_complete,null)
        val  button = view.findViewById<TextView>(R.id.submittv)
        val  exitbutton = view.findViewById<TextView>(R.id.exittv)
        val messageSelectedText = view.findViewById<TextView>(R.id.messageSelectedText)
        val text_msg = view.findViewById<TextView>(R.id.text_msg)
        val text_points = view.findViewById<TextView>(R.id.text_points)





       // messageSelectedText.text = "Your Score is ${correctAnswer}/5"
        if(isFinish) {
            messageSelectedText.visibility = View.VISIBLE
            text_msg.text = "Sorry, the answer is incorrect. Redeem XXMB or Continue to win XXGB!"
            text_msg.visibility= View.VISIBLE
        }
       else if(correctAnswer<5)
        {
           // val wans=5-correctAnswer
            val wans=100*qnumber
           // text_msg.text = "You have given ${wans} wrong answers but"
            text_msg.text = "Sorry, the answer is incorrect. You won ${wans} MB amount of data!"
            text_msg.visibility= View.VISIBLE
           // text_points.text = "You Got 50 Points"
        }


        builder.setView(view)
        button.setOnClickListener {
            builder.dismiss()
            val inLogin = Intent(context, PurchasePacksActivity::class.java)
            startActivity(inLogin)
            finish()
        }
        exitbutton.setOnClickListener {
            builder.dismiss()
            val inLogin = Intent(context, QuizMenuActivity::class.java)
            startActivity(inLogin)
            finish()
        }
        builder.setCanceledOnTouchOutside(false)
        builder.show()
       // val mDialogView = LayoutInflater.from(context).inflate(R.layout.dialog_complete, null)
      //  val mAlertDialog = AlertDialog.Builder(context).setView(mDialogView).show()
        /*mAlertDialog.submit.setOnClickListener {
                    val inLogin = Intent(context, PurchasePacksActivity::class.java)
                    startActivity(inLogin)
                    finish()
        }*/

    }

    override fun onSelectClicked(optionName: String?, type: String?) {
        selectedOptionType = type!!
        Log.e("onSelectClicked",":"+selectedOptionType)
    }

    fun playSound() {

        Log.e("playSound",":"+qnumber)

        var audioUrl =   quizlist.get(qnumber).question

        Log.e("playSound",":"+audioUrl)
       // var audioUrl =  user.response.get(qnumber).songurl
        //   if (mediaPlayer == null) {
        if(audioUrl.equals("whenyouarenotwithme"))
            mediaPlayer = MediaPlayer.create(this, R.raw.whenyouarenotwithme)
        else if(audioUrl.equals("thevictorianmansion"))
            mediaPlayer = MediaPlayer.create(this, R.raw.thevictorianmansion)
        else if(audioUrl.equals("travellingbluesexplorer"))
            mediaPlayer = MediaPlayer.create(this, R.raw.travellingbluesexplorer)
        else if(audioUrl.equals("howfarisitto"))
            mediaPlayer = MediaPlayer.create(this, R.raw.howfarisitto)
        else if(audioUrl.equals("daretowin"))
            mediaPlayer = MediaPlayer.create(this, R.raw.daretowin)
        mediaPlayer!!.isLooping = true
        mediaPlayer!!.start()
        // } else mediaPlayer!!.start()
    }

    // 2. Pause playback
    fun pauseSound() {
        if (mediaPlayer?.isPlaying == true) mediaPlayer?.pause()
    }

    // 3. Stops playback
    fun stopSound() {
        try {
            if (mediaPlayer != null) {
                if (mediaPlayer?.isPlaying == true) {
                    mediaPlayer!!.stop()
                    mediaPlayer!!.release()

                }
                //  mediaPlayer = null
            }
        }
        catch (e:java.lang.Exception)
        {

        }
    }

    override fun onBackPressed() {
        super.onBackPressed()
    }
    override fun onDestroy() {
        super.onDestroy()
        stopSound()
        if(timer!=null) {
            timer?.cancel()
            timerStarted = false
        }
    }

    private fun setupViewModel() {
        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(
                ApiHelperImpl(RetrofitBuilder.apiService)

            )
        )[MainViewModel::class.java]

        viewModel.getUiState().observe(this) {
            when (it) {
                is UiState.Success -> {
                    LoadingUtils.hideDialog()
                    Log.e("success",":"+it.data)
                   // progressBar.visibility = View.GONE
                   // renderList(it.data)
                    quizlist=it.data
                    progressbar_id.max=quizlist.size
                    percentData.text=qnumber.toString()+"/"+quizlist.size.toString()
                    setUpQuizAdapterData(it.data)
                    startTimer()
                }
                is UiState.Loading -> {
                    LoadingUtils.showDialog(this@MainActivity,true)
                    Log.e("Loading",":1")
                 //   progressBar.visibility = View.VISIBLE

                }
                is UiState.Error -> {
                    LoadingUtils.hideDialog()
                    //Handle Error
                   // progressBar.visibility = View.GONE
                    Log.e("Error",":roor"+it.message)
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        }
        viewModel.getResponseState().observe(this)
        {
            when(it)
            {
                is UiState.Success -> {
                    LoadingUtils.hideDialog()
                    Log.e("success",":"+it.data)
                    // progressBar.visibility = View.GONE
                    // renderList(it.data)
                   // if(it.data.status=="Created")
                    if(question_status=="processing")
                    moveToNextSong()

                }
                is UiState.Loading -> {
                    Log.e("Loading",":1")
                    //   progressBar.visibility = View.VISIBLE

                }
                is UiState.Error -> {
                    LoadingUtils.hideDialog()
                    //Handle Error
                    // progressBar.visibility = View.GONE
                    Log.e("Error",":roor"+it.message)
                    Toast.makeText(this, it.message, Toast.LENGTH_LONG).show()
                }
            }
        }
    }
    private fun onBackClickedDialog(){


        val builder = AlertDialog.Builder(this).create()
        val view = layoutInflater.inflate(R.layout.dialog_exit,null)
        val  okbutton = view.findViewById<TextView>(R.id.okData)

        val  cancelbutton = view.findViewById<TextView>(R.id.cancelText)

        builder.setView(view)
        okbutton.setOnClickListener {
            builder.dismiss()

            finish()
        }
        cancelbutton.setOnClickListener {
            builder.dismiss()

        }
        builder.setCanceledOnTouchOutside(false)
        builder.show()


    }
}

/*@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = stringResource(id = R.string.app_name),
       modifier = modifier.fillMaxSize(),
        color = colorResource(id = R.color.body_bg),
        fontFamily = (FontFamily(Font(R.font.kavoon))),
        fontSize = 18.sp
    )
}*/

/*
@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    GuessTheSongTheme {
        Greeting("Android")
    }
}*/
