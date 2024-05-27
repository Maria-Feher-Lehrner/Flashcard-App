package com.example.flashcards

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity

class SocialsActivity: AppCompatActivity() {

    val LOG_TAG = "SocialsActivity"
    lateinit var sharedProgressTextView: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_socials)

        sharedProgressTextView = findViewById(R.id.tv_socials)
        val textFromIntent = intent.getStringExtra((Intent.EXTRA_TEXT) ?: "nothing")
        Log.i(LOG_TAG, "Text from sharing: $textFromIntent")
        sharedProgressTextView.text = textFromIntent
    }
}