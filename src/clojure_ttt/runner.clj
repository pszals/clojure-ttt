(ns clojure-ttt.runner
  (:require [clojure-ttt.board :refer :all]
            [clojure-ttt.input-output :refer :all]
            [clojure-ttt.players :refer :all]))

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
