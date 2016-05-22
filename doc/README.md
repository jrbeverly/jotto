# Jotto Game 
***
#### Summary

Jotto is a logic-oriented word game played with two players. Each player picks a secret word of five letters (that is in the dictionary), and the object of the game is to correctly guess the other player's word first. Players take turns guessing and giving the number of Jots, or the number of letters that are in both the guessed word and the secret word.

##### Features List

* MVC Design for User Interface
* Listener/EventMap Design
* Multiple State Views (Letterboard / Guessboard)	
* Dictionary Variants
	
##### Architecture Notes	

* **Core**
	* Structured to be based around JDictionary and Jotto
	* Handles the majority of game logic
* **Application**
	* Model
		* Application Logic defined by *Jotto*
		* Defines models and related structures (dictionaries/history)
		* Additional logic defined for application
	* Controllers
		* `JottoEventMap` - Controls mapping of listeners that game events
		* `JottoListener` - The base listener for handling specific game events (win/loss/match begin)
	* View
		* Views that react based on a specific jotto listener
		* Specific views are Letterboard and Guessboard
		
By the design of the architecture, the model has no knowledge of how the listeners or dispatching of updates occurs.  Instead the `JottoEventMap` calls events that notify listeners on actions or property changes from business logic.  The listeners can then be used to notify the `View` of changes.  The design goal was to separate the `View`, `Controller (Listener)` and `Model` design as much as possible.  The structure and design on the application does need improvement in overall modularity.