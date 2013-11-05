(ns clojure-ttt.ai
  (:require [clojure-ttt.board :refer :all]))

  (defn max-value [squares-with-values]
    (key (apply max-key val squares-with-values)))

  (defn minimax [board piece depth scores])

  (defn ai-move [board piece] ["x" "x" "x" 4 5 6 7 8 9])

  (defn score-board [board piece]
    (if (winner? board)
      (if (= piece (winning-piece board))
        1.0
        -1.0)
      (if (full? board)
        0)))

  
