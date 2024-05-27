package com.example.flashcards.adapter

import android.content.Context
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView.OnItemClickListener
import android.widget.TextView
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.flashcards.data.WordPair
//import com.example.flashcards.databinding.WordItemBinding
import com.example.flashcards.R
import com.example.flashcards.WordListFragmentDirections

const val LOG_TAG = "WordListAdapter"

class WordListAdapter(
    private var wordList: ArrayList<WordPair>/*,
    private val context: Context,
    private val itemClickListener: ((WordPair) -> Unit)*/
) : RecyclerView.Adapter<WordPairHolder>() {
    /*var wordList = wordList
        set(value) {
            field = value
            notifyDataSetChanged()
        }*/

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WordPairHolder {
        Log.e(LOG_TAG, "ON CREATE VIEWHOLDER")
        val listItemRootView =
            LayoutInflater.from(parent.context).inflate(R.layout.view_wordpair, parent, false)
        return WordPairHolder(listItemRootView)
        //val binding = WordItemBinding.inflate(LayoutInflater.from(context), parent, false)
        //return WordPairHolder(binding)
    }

    override fun getItemCount() = wordList.size

    override fun onBindViewHolder(holder: WordPairHolder, position: Int) {
        //Log.e(LOG_TAG, "ON BIND VIEWHOLDER $position")
        val wordPair = wordList[position]
        holder.germanWord.text = wordPair.wordGER
        holder.englishWord.text = wordPair.wordEN

        /*holder.bindToWordPair(wordPair)
        holder.itemView.setOnClickListener{
            itemClickListener(wordPair)
        }*/
    }
}

class WordPairHolder(rootView: View) :
    RecyclerView.ViewHolder(rootView) {
    val germanWord: TextView
    val englishWord: TextView

    init {
        germanWord = rootView.findViewById(R.id.germanWord)
        englishWord = rootView.findViewById(R.id.englishWord)

        /*rootView.setOnClickListener {
            val navHostFragment = rootView.findNavController()
            navHostFragment.navigate(
                WordListFragmentDirections.actionWordListFragmentToWordItemsFragment("TODO: Daten weitergeben")
            )
        }*/
    }
}




