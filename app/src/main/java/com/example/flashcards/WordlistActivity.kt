package com.example.flashcards

import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.NavHostFragment
import com.example.flashcards.data.WordPair
import com.example.flashcards.databinding.ActivityWordlistBinding


class WordlistActivity : AppCompatActivity() {

    val LOG_TAG = "WordlistActivity"
    private lateinit var binding: ActivityWordlistBinding
    private var transferredWordList = mutableMapOf<String, WordPair>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        Log.i(LOG_TAG, "onCreate")
        binding = ActivityWordlistBinding.inflate(layoutInflater)
        setContentView(binding.root)

        transferredWordList = ((intent.getSerializableExtra(EXTRA_KEY_WORDLIST) as? HashMap<String, WordPair>)!!)
        val wordPairList = ArrayList(transferredWordList.values) ?: emptyList()

        val navHostFragment = supportFragmentManager.findFragmentById(R.id.fragment_container) as NavHostFragment
        val navController = navHostFragment.navController

        val bundle = Bundle()
        bundle.putSerializable(WordListFragment.ARG_WORD_LIST, ArrayList(wordPairList))
        navController.navigate(R.id.wordListFragment, bundle)

        Log.e(LOG_TAG, "onCreate")
    }

    override fun onStart() {
        super.onStart()
        Log.i(LOG_TAG, "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.i(LOG_TAG, "onResume")
    }

    override fun onRestart() {
        super.onRestart()
        Log.i(LOG_TAG, "onRestart")
    }
}