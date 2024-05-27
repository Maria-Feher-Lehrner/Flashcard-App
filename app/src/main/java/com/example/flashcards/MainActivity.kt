package com.example.flashcards

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextUtils.replace
import android.text.TextWatcher
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.commit
import com.example.flashcards.data.WordPair
import com.example.flashcards.databinding.ActivityMainBinding
import com.google.android.material.floatingactionbutton.FloatingActionButton

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    val LOG_TAG = "MainActivity"

    lateinit var inputGer: EditText
    lateinit var inputEn: EditText
    val wordList = mutableMapOf<String, WordPair>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        Log.e(LOG_TAG, "onCreateInstanceState called")

        initializeWordList()

        val saveButton = findViewById<Button>(R.id.btn_save)
        val quizButton = findViewById<Button>(R.id.btn_quiz)
        val deleteButton = findViewById<Button>(R.id.btn_delete)
        val fab = findViewById<FloatingActionButton>(R.id.floatingActionButton)

        //TODO: Am Ende Testfeld rausnehmen.
        //testfeld
        val testField = findViewById<TextView>(R.id.test)

        fab.setOnClickListener {
            //TODO: hier noch sinnvollere action einfuegen!
            Log.d(LOG_TAG, "FAB Button was clicked")
        }

        inputGer = findViewById(R.id.eT_language1)
        inputEn = findViewById(R.id.eT_language2)

        val textWatcherGer = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                Log.d(LOG_TAG, "inputGer after TextChanged: ${s?.length ?: 0} characters")
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        }

        val textWatcherEn = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                Log.d(LOG_TAG, "inputEn afterTextChanged: ${s?.length ?: 0} characters")
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {}
            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {}
        }

        inputGer.addTextChangedListener(textWatcherGer)
        inputEn.addTextChangedListener(textWatcherEn)
        testField.text = wordList.toString()

        saveButton.setOnClickListener {
            val words = readWords()
            val wordGer = words.first
            val wordEn = words.second

            wordList[wordGer] = WordPair(wordGer, wordEn, 0)
            inputGer.text.clear()
            inputEn.text.clear()

            testField.text = wordList.toString()
        }

        quizButton.setOnClickListener {
            //Hier hatte ich Schwierigkeiten meine Liste in einer für mich naheliegenderen Form
            // zu übergeben. Array hat nicht funktioniert, deswegen ist es zur Hashmap mit gewachsen
            // mit jetzt den deutschen Wörtern als keys, aber ich hab die gleichzeitig auch als
            // Property im Datenobjekt gebraucht...
            val intent = Intent(this, QuizActivity::class.java)
                 val wordListAsSerializable = HashMap(wordList)
                 intent.putExtra(EXTRA_KEY_WORDLIST, wordListAsSerializable)
                 wordListAsSerializable.forEach { (key, value) ->
                     println("Key: $key, Value: $value")
            }

            startActivity(intent)
        }

        binding.btnEdit.setOnClickListener{
            val intent = Intent(this, WordlistActivity::class.java)
            val wordListAsSerializable = HashMap(wordList)
            intent.putExtra(EXTRA_KEY_WORDLIST, wordListAsSerializable)
            startActivity(intent)
        }

        deleteButton.setOnClickListener {
            wordList.clear()
            testField.text = wordList.toString()
        }
    }

    fun readWords(): Pair<String, String> {
        val word1 = inputGer.text.toString()
        val word2 = inputEn.text.toString()
        return word1 to word2
    }

    private fun initializeWordList() {
        wordList["Vater"] = WordPair("Vater","father", 0)
        wordList["Mutter"] = WordPair("Mutter","mother", 0)
        wordList["Kind"] = WordPair("Kind", "child", 0)
        wordList["Hund"] = WordPair("Hund", "dog", 0)
        wordList["Katze"] = WordPair("Katze", "cat", 0)
        wordList["Maus"] = WordPair("Maus", "mouse", 0)
    }

    override fun onResume() {
        super.onResume()
        Log.e(LOG_TAG, "onResumeInstanceState called")
    }

    override fun onStart() {
        super.onStart()
        Log.e(LOG_TAG, "onStart")
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
}