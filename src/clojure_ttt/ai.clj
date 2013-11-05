(ns clojure-ttt.ai
  (:require [clojure-ttt.board :refer :all]))

  (defn ai-move [board] 3)

  (defn score-board [board piece]
    (if (winner? board)
      (if (= piece (winning-piece board))
        1
        -1)
      (if (full? board)
        0)))

