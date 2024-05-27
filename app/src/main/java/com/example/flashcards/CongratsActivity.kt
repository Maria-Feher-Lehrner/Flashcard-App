package com.example.flashcards

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity

class CongratsActivity : AppCompatActivity() {

    val LOG_TAG = "CongratsActivity"


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_congrats)

        Log.e(LOG_TAG, "onCreate")

    }

    override fun onStart() {
        super.onStart()
        Log.e(LOG_TAG, "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.e(LOG_TAG, "onResume")
    }

    override fun onRestart() {
        super.onRestart()
        Log.e(LOG_TAG, "onRestart")
    }
}