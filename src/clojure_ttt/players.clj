(ns clojure-ttt.players
  (:require [clojure-ttt.board :refer :all]
            [clojure-ttt.input-output :refer :all]
            [clojure-ttt.ai :refer :all]))
  
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

  (defrecord Computer [piece]
    Player
    (marker [this] (:piece this))
    (take-turn [this board] (ai-move board piece)))

  (defn create-player [piece]
    (if (= piece "x")
      (Human. piece)
      (Computer. piece)))
