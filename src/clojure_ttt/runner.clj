(ns clojure-ttt.runner
  (:require [clojure-ttt.board :refer :all]
            [clojure-ttt.input-output :refer :all]))

  (defn human-move [board]
    (loop [user-input (input (instructions))]
      (if (validate-input user-input) 
        (if (open? board (Integer. user-input))
          (Integer. user-input)
          (recur (input (instructions))))
        (recur (input (instructions))))))

  (defprotocol Player
    (marker [this])
    (take-turn [this board])) 

  (defrecord Human [piece]
    Player
    (marker [this] (:piece this))
    (take-turn [this board] (place-piece (human-move board) piece board)))  

  (defn create-player [piece]
    (Human. piece))

  (defn play-game [board]
    (do 
      (output (new-lines board))
      (prompt-move (piece-to-play board)))
    (let [new-board (take-turn (create-player (piece-to-play board)) board)]
      (if (game-over? new-board)
        (output (game-over-message new-board))
        (recur new-board)))) 

(defn -main []
  (play-game [1 2 3 4 5 6 7 8 9]))
