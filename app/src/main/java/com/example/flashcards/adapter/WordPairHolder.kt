/*package com.example.flashcards.adapter

import android.view.View
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.flashcards.R
import com.example.flashcards.WordListFragment
import com.example.flashcards.WordListFragmentDirections

class WordPairHolder(rootView: View) :
    RecyclerView.ViewHolder(rootView) {
    val germanWord: TextView
    val englishWord: TextView

    init{
        germanWord = rootView.findViewById(R.id.germanWord)
        englishWord = rootView.findViewById(R.id.englishWord)
        rootView.setOnClickListener({
            val navHostFragment = rootView.findNavController()
            navHostFragment.navigate(
                WordListFragmentDirections.actionWordListFragmentToWordItemsFragment("TODO: Daten weitergeben")
            )
        })
    }
}*/