(ns clojure-ttt.runner
  (:require [clojure-ttt.board :refer :all]
            [clojure-ttt.input-output :refer :all]))

  (defn play-game [board]
    (output (new-lines [board]))  
    (prompt-move (piece-to-play))
    )

  (defn human-move [board]
    (loop [user-input (input (instructions))]
      (if (validate-input user-input) 
        (if (open? board (Integer. user-input))
          (Integer. user-input)
          (recur (input (instructions))))
        (recur (input instructions)))))

  (defprotocol Player
    (marker [this])
    (take-turn [this board])) 

  (defrecord Human [piece]
    Player
    (marker [this] (:piece this))
    (take-turn [this board] (place-piece (human-move board) piece board)))  

  (defn create-player [piece]
    (Human. piece))
