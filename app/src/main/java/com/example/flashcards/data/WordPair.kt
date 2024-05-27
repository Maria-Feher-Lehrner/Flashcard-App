package com.example.flashcards.data

import java.io.Serializable

data class WordPair(
    val wordGER: String,
    val wordEN: String,
    var score: Int
) : Serializable