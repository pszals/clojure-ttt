(ns clojure-ttt.ai
  (:require [clojure-ttt.board :refer :all]))

  (defn max-value [squares-with-values]
    (key (apply max-key val squares-with-values)))

  ;(defn minimax [board piece depth scores]
  ;  (if (game-over? board)
  ;    (/ (score-board board piece) depth)
  ;    (for [square (list-empty-squares board)]
  ;      (let [new-board (place-piece square piece board)
  ;            score (minimax 
  ;                    new-board
  ;                    (opponent-piece board)]
  ;      (recur 
  ;        new-board 
  ;        (opponent-piece board) 
  ;        (+ 1 depth) 
  ;        (assoc scores square score)))))))

(defn generate-one-level-deep [board]
  (let [possible-moves (list-empty-squares board)]
    (for [square possible-moves]
      (place-piece square (piece-to-play board) board))))

  (defn score-board [board piece]
    (if (winner? board)
      (if (= piece (winning-piece board))
        1.0
        -1.0)
      (if (full? board)
        0)))

(defn minimax [board depth maximizing? best-val]
  ["x" "x" "x" "o" "x" "o" "o" "o" "x"])
; (cond 
;   (game-over? board) (score-board board (piece-to-play board))
;   maximizing? 
;     (for [board (generate-one-level-deep board)]
;       (let [best-val (max 
;                        best-val 
;                       (minimax 
;                         board
;                         (+ 1 depth)
;                         (not maximizing?)
;                         best-val))]
;         best-val))
;   :else 
;     (for [board (generate-one-level-deep board)]
;       (let [best-val (min best-val 
;                       (minimax 
;                         board
;                         (+ 1 depth)
;                         (not maximizing?)
;                         best-val))]
;         best-val))))
    

  (defn board-with-ai-move [board piece] (minimax board 0 true 0))
