package com.example.flashcards

import android.Manifest
import android.app.NotificationChannel
import android.app.NotificationManager
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.BitmapFactory
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.ImageButton
import android.widget.TextView
import android.widget.ToggleButton
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import androidx.core.content.ContextCompat
import com.example.flashcards.data.WordPair

class QuizActivity : AppCompatActivity() {

    val LOG_TAG = "QuizActivity"

    private lateinit var quizView: TextView
    private lateinit var toggleLanguage: ToggleButton
    private lateinit var currentWordKey: String
    private var transferredWordList: HashMap<String, WordPair>? = null
    private var notificationId = 1


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quiz)
        Log.e(LOG_TAG, "onCreate")

        transferredWordList =
            intent.getSerializableExtra(EXTRA_KEY_WORDLIST) as? HashMap<String, WordPair>
        if (transferredWordList != null) {
            Log.i(LOG_TAG, "Content of transferredWordList is $transferredWordList")
        } else {
            Log.e(LOG_TAG, "Failed to retrieve wordList from intent extras")
        }
        toggleLanguage = findViewById<ToggleButton>(R.id.toggleButton)
        quizView = findViewById<TextView>(R.id.tv_checkWord)
        val quizNext = findViewById<Button>(R.id.btn_quiz_next)
        val quizCheck = findViewById<Button>(R.id.btn_check)

        val checkPositive = findViewById<ImageButton>(R.id.btn_checkPositive)
        val checkNegative = findViewById<Button>(R.id.btn_checkNegative)
        val quitButton = findViewById<Button>(R.id.btn_end_quiz)
        val imageButton = findViewById<ImageButton>(R.id.imgbtn_share)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            if (ContextCompat.checkSelfPermission(this, Manifest.permission.POST_NOTIFICATIONS) != PackageManager.PERMISSION_GRANTED) {
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.POST_NOTIFICATIONS), REQUEST_NOTIFICATION_PERMISSION)
            }
        }

        imageButton.setOnClickListener {
            val shareableResult = transferredWordList.toString()
            Log.i(LOG_TAG, "content of shareableResult is $shareableResult")
            val implicitIntent = Intent(Intent.ACTION_SEND)
            implicitIntent.type = "text/plain"
            implicitIntent.putExtra(Intent.EXTRA_TEXT, "Look at all the words I've already learned! $shareableResult")
            val chooser = Intent.createChooser(
                implicitIntent, "Share your progress"
            )
            startActivity(chooser)
        }

        quizNext.setOnClickListener {
            transferredWordList?.let {
                val randomWord = provideRandomizedWord(it)
                quizView.text = randomWord
            }
        }

        quizCheck.setOnClickListener {
            transferredWordList?.let {
                val checkWord = quizView.text.toString()
                quizView.text = getCheckWord(checkWord, it)
            }
        }

        checkPositive.setOnClickListener {
            updateScore(1)
            Log.i(LOG_TAG, "Updated score of transferredWordList is $transferredWordList")

            //Trigger fuer Notification:
            val wordPair = transferredWordList?.get(currentWordKey)
            if (wordPair != null) {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
                    if (wordPair.score == 3) {
                        showScoreNotification(this, quizView.text.toString())
                    }
                } else {
                    showScoreNotification(this, quizView.text.toString())
                }
            }
        }

        checkNegative.setOnClickListener {
            val wordPair = transferredWordList?.get(currentWordKey)
            if (wordPair != null) {
                if (wordPair.score > 0) {
                    updateScore(-1)
                }
            }
            Log.i(LOG_TAG, "Updated score of transferredWordList is $transferredWordList")
        }

        quitButton.setOnClickListener {
            val intent = Intent(this@QuizActivity, MainActivity::class.java)
            startActivity(intent)
            finish()
        }
    }

    private fun createNotificationChannel(context: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channelId = CHANNEL_ID
            val channelName = "Word Score Notifications"
            val notificationChannel =
                NotificationChannel(CHANNEL_ID, channelName, NotificationManager.IMPORTANCE_DEFAULT)
            val notificationManager = NotificationManagerCompat.from(context)
            notificationManager.createNotificationChannel(notificationChannel)
        }
    }

    private fun showScoreNotification(context: Context, word: String) {
        createNotificationChannel(context)
        val builder = NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Gratulation!")
            .setContentText("Du hast einen Lernpunktestand von 3 mit '$word' erreicht!")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setPriority(NotificationCompat.PRIORITY_DEFAULT)
            .setAutoCancel(true)
        val largeIcon =
            BitmapFactory.decodeResource(context.resources, R.drawable.ic_launcher_foreground)

        builder.setLargeIcon(largeIcon)
        val notificationManager = NotificationManagerCompat.from(context)
        notificationManager.notify(notificationId++, builder.build())
    }

    private fun provideRandomizedWord(wordList: HashMap<String, WordPair>): String {
        var displayedWord = ""
        if (wordList.isNotEmpty()) {
            val keys = wordList.keys.toList()
            currentWordKey = keys.random()
            val wordPair = wordList[currentWordKey]
            if (wordPair != null) {
                displayedWord = if (toggleLanguage.isChecked) wordPair.wordGER else wordPair.wordEN
            }
        }
        return displayedWord
    }

    private fun updateScore(delta: Int) {
        val wordPair = transferredWordList?.get(currentWordKey)
        if (wordPair != null) {
            wordPair.score += delta
        }
    }

    private fun getCheckWord(quizWord: String, wordList: HashMap<String, WordPair>): String? {
        return if (toggleLanguage.isChecked) {
            wordList.entries.find { it.value.wordGER == quizWord }?.value?.wordEN
        } else {
            wordList.entries.find { it.value.wordEN == quizWord }?.value?.wordGER
        }
    }

    override fun onStart() {
        super.onStart()
        Log.e(LOG_TAG, "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.e(LOG_TAG, "onResume")
    }

    override fun onStop() {
        super.onStop()
        Log.e(LOG_TAG, "onStop")
    }

    override fun onPause() {
        super.onPause()
        Log.e(LOG_TAG, "onPause")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(LOG_TAG, "onDestroy")
    }

    override fun onRestart() {
        super.onRestart()
        Log.e(LOG_TAG, "onRestart")
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        if (requestCode == REQUEST_NOTIFICATION_PERMISSION) {
            if ((grantResults.isNotEmpty() && grantResults[0] == PackageManager.PERMISSION_GRANTED)) {
                Log.i(LOG_TAG, "Notification permission granted.")
            } else {
                Log.i(LOG_TAG, "Notification permission denied.")
            }
        }
    }
}