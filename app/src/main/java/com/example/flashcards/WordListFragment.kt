package com.example.flashcards

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.flashcards.adapter.WordListAdapter
import com.example.flashcards.data.WordPair
import com.example.flashcards.databinding.FragmentWordlistBinding

class WordListFragment : Fragment() {

    val LOG_TAG = "WordlistFragment"
    private var wordPairList: ArrayList<WordPair>? = null

    private var _binding: FragmentWordlistBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.e(LOG_TAG, "onCreateView")
        _binding = FragmentWordlistBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        Log.e(LOG_TAG, "onViewCreated")

        wordPairList = arguments?.getSerializable(ARG_WORD_LIST) as? ArrayList<WordPair>

        val recyclerView = binding.rvWordList
        val linearLayoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)
        val divider = DividerItemDecoration(requireContext(), linearLayoutManager.orientation)

        val adapter = WordListAdapter(wordPairList ?: arrayListOf(), this.requireContext()
        ){
            val action = WordListFragmentDirections.actionWordListFragmentToWordItemsFragment(it)
            findNavController().navigate(action)
        }
        recyclerView.layoutManager = linearLayoutManager
        recyclerView.adapter = adapter
        recyclerView.addItemDecoration(divider)
    }

    companion object {
        const val ARG_WORD_LIST = "word_list"
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.e(LOG_TAG, "onDestroy")
        _binding = null
    }
}