package numberguessing

import upickle.default.*

val MAX_ATTEMPTS = 5
val MIN_BETWEEN = 0 
val MAX_BETWEEN = 99


case class Game(number: Int, var state: GameState)

enum GameState:
  case Win
  case Lose
  case Continue(attempts_remaining: Int, hint: Hint)

enum Hint derives ReadWriter:
  case None
  case Smaller
  case Bigger

/* {"guess": 12} */
case class PlayerGuess(number: Int)  derives ReadWriter 

// {"$type": "Wrong", "hint": {"$type": "SmallerThan"}}
// {"$type": "Correct", "answer": 3}
enum ServerResponse derives ReadWriter:
  case Wrong(hint: Hint)
  case Correct
  case Result(number: Int)
  
  
class WrongResponseException extends Exception
