(ns clojure-ttt.ai
  (:require [clojure-ttt.board :refer :all]))

  (defn max-value [squares-with-values]
    "Gets the square with the highest score"
    (key (apply max-key val squares-with-values)))

  (defn generate-one-level-deep [piece board]
    (let [possible-moves (list-empty-squares board)]
      (for [square possible-moves]
        (place-piece square piece board))))

  (defn score-board [board piece]
    (if (winner? board)
      (if (= (winning-piece board) piece)
        1.0
        -1.0)
      (if (full? board)
        0)))

  (defn minimax [board minimizing? depth]
    (if (game-over? board)
      (/ (score-board board "o") depth)
      (if minimizing?
        (apply min (map #(minimax % (not minimizing?) (inc depth))
                        (generate-one-level-deep (piece-to-play board) board)))
        (apply max (map #(minimax % (not minimizing?) (inc depth))
                        (generate-one-level-deep (piece-to-play board) board))))))

  (defn scores-with-moves [board]
    (let [moves (list-empty-squares board)]
      (zipmap moves 
        (map 
          #(minimax (place-piece % (piece-to-play board) board) true 1) 
          moves))))

  (defn ai-move [board]
    (max-value (scores-with-moves board)))

  (defn board-with-ai-move [board] 
    (place-piece (ai-move board) (piece-to-play board) board))
