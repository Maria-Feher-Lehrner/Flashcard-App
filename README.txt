++++FIRST INDEPENDENTLY PROGRAMMED ANDROID APP IN KOTLIN AS FINAL CLASS PROJECT IN ANDROID BASICS++++

FLASHCARDS APP

This Flashcards App is designed to help users learn new vocabulary through a flashcard system. The app allows users to add new word pairs, view a list of word pairs, and quiz themselves on the vocabulary. Further functionality (like editing the word pairs) is yet to be implemented.

Features

    Add Word Pairs: Users can add new word pairs to their word list.
    View Word List: Users can view a list of all the word pairs they have added.
    Quiz Mode: Users can quiz themselves on the word pairs.
    Word Details: Users can view details of individual word pairs.
	
EXERCISE BLOCKS REALIZED

The app implements the following exercise blocks:

    Base Functionality:
        TextInputEditText: Adding word pairs in MainActivity
		Listening for text changes: vals textWatcher in MainActivity
		FAB: in MainActivity
        Viewing word list in WordListFragment
        Word pair data class in WordPair.kt
        Adapter and ViewHolder for RecyclerView in WordListAdapter and WordPairHolder
        Navigation setup in nav_graph.xml

    Block 1: Navigation
        Intents: 
			Between MainActivity and QuizActivity 
			& from MainActivity to WordListActivity
		Pass Arguments with Intent:
			From MainActivity to QuizActivity
		Pass Arguments with navigation action:
			From WordListFragment to WordItemsFragment

    Block 3: RecyclerView & Lists
        RecyclerView: WordListFragment
		List item Layout: view_wordpair.xml
		ViewHolder: WordPairHolder
		Adapter: WordListAdapter
		Layout manager: WordListFragment
		Action when list item gets clicked: navigation to WordItemsFragment with detail-view

    Block 6: Notifications
        Display a notification when a trigger gets activated:
			QuizActivity: reaching three points for a single wordPair (click check positive btn) triggers notification
			
	Block 7: Sharing is caring
        Display a simple text from and to the app:
			From QuizActivity to SocialsActivity (Short message + wordlist Array)
