# Jotto Game #
##### Language: *Java* 
***
#### Abstract
The Jotto game was designed as an assignment for a user interfaces course.  Its purpose was to focus on software architectural pattern, Model-View-Controller (MVC).  The expanded version of this assignment offers more options and controls that previous, and additional separation between Presentation (View) and the Model (Application Model). 

Specifically the application is separated into two components: **JottoApp** and **Jotto*.  **Jotto** is the main core logic of the application.  It includes the ability to specify different game modes, and controls the overall logic of a Jotto game.  **JottoApp** is a front-end for the Jotto game implemented in the standard java swing libraries.

***
#### Summary

Jotto is a logic-oriented word game played with two players. Each player picks a secret word of five letters (that is in the dictionary), and the object of the game is to correctly guess the other player's word first. Players take turns guessing and giving the number of Jots, or the number of letters that are in both the guessed word and the secret word.

##### Features List

* Multiple Game Modes (World Master Mind, Five Letter, Four Letter, Lingo, Kane Jotto, Three Letter)
* Client/Server Architecture
* MVC Design for User Interface
* Multiple State Views (Letterboard / Guessboard)	
* Dictionary Variants
	

##### Architecture Notes	

* **Jotto**
	* Note
	* Note
	* Note
* **JottoApp**
	* Model
		* Application Logic defined by *Jotto*
		* Defines models and related structures (dictionaries/history)
		* Additional logic defined for application
	* Controllers
		* `JottoEventMap` - Controls mapping of listeners that game events
		* `JottoListener` - A listener for handling specific game events (win/loss/match begin)
	* View
		* Views that react based on a specific jotto listener
		
By the design of the architecture, the model has no knowledge of how the listeners or dispatching of updates occurs.  Instead the `JottoEventMap` receives notifications of property changes from business logic, and forwards these notifications to the listeners.  The listeners can then be used to notify the `View` of changes.