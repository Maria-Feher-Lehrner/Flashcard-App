package com.example.flashcards.adapter

import android.view.View
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.flashcards.R


class WordPairHolder(rootView: View) :
    RecyclerView.ViewHolder(rootView) {
    val germanWord: TextView
    val englishWord: TextView

    init {
        germanWord = rootView.findViewById(R.id.germanWord)
        englishWord = rootView.findViewById(R.id.englishWord)
    }
}