package com.example.flashcards.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.flashcards.data.WordPair
import com.example.flashcards.R

const val LOG_TAG = "WordListAdapter"

class WordListAdapter(
    private var wordList: ArrayList<WordPair>,
    private val context: Context,
    private val itemClickListener: ((WordPair) -> Unit)
) : RecyclerView.Adapter<WordPairHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordPairHolder {
        Log.e(LOG_TAG, "ON CREATE VIEWHOLDER")
        val listItemRootView =
            LayoutInflater.from(parent.context).inflate(R.layout.view_wordpair, parent, false)
        return WordPairHolder(listItemRootView)
    }

    override fun getItemCount() = wordList.size

    override fun onBindViewHolder(holder: WordPairHolder, position: Int) {
        val wordPair = wordList[position]
        holder.germanWord.text = wordPair.wordGER
        holder.englishWord.text = wordPair.wordEN

        holder.itemView.setOnClickListener{
            itemClickListener(wordPair)
        }
    }
}
