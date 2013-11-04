(ns clojure-ttt.input-output
  (:require [clojure-ttt.board :refer [rows winning-piece]]
            [clojure.string :refer [join]]))

  (defn prompt-move [piece]
    (str "It's player " piece "'s turn"))

  (defn game-won-by [piece]
    (str "Player " piece " wins!"))

  (defn tie-game []
    "Tie game!")

  (defn game-over-message [board]
    (if (nil? (winning-piece board))
      (tie-game)
      (game-won-by (winning-piece board))))

  (defn instructions []
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
    (let [value (re-seq #"^\d{1}$" in)]
      (not (nil? value))))
