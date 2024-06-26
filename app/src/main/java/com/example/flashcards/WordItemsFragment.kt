package com.example.flashcards

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.flashcards.databinding.FragmentWorditemsBinding


class WordItemsFragment: Fragment() {

    private var _binding: FragmentWorditemsBinding? = null
    val LOG_TAG = "WordItemsFragment"

    private val binding get() = _binding!!
    val fragmentArgs: WordItemsFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentWorditemsBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e("WordPairItem", "${fragmentArgs.wordPair}")

        binding.tvWordGER.text = fragmentArgs.wordPair.wordGER
        binding.tvWordEN.text = fragmentArgs.wordPair.wordEN

        /*TODO: Uebergabe von arguments beim Zuruecknavigieren fixen.
        binding.btnBack.setOnClickListener{
            findNavController().navigate(R.id.action_WordItemsFragment_to_WordListFragment)
        }*/
    }

    override fun onStart() {
        super.onStart()
        Log.i(LOG_TAG, "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.i(LOG_TAG, "onResume")
    }

}