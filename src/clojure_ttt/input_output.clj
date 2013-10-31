(ns clojure-ttt.input-output
  (:require [clojure-ttt.board :refer [rows]]
            [clojure.string :refer [join]]))

  (defn prompt-move [piece]
    (str "It's player " piece "'s turn"))

  (defn game-won-by [piece]
    (str "Player " piece " wins!"))

  (defn tie-game []
    "Tie game!")

  (defn invalid []
    "Please enter a number between 1 and 9")

  (defn output [message]
    (println message))

  (defn input [message]
    (println message)
    (read-line))

  (defn row-pipes [a-set]
    (conj '() (join " | " a-set)))

  (defn board-pipes [board]
    (map row-pipes (rows board)))

  (defn new-lines [board]
   (apply str (flatten (interpose "\n---------\n" (board-pipes board)))))

  (defn validate-input [in]
    (and (> (Integer. in) 0) (< (Integer. in) 10)))
