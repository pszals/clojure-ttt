(ns clojure-ttt.ai-spec
  (:require [speclj.core :refer :all]
            [clojure-ttt.board :refer :all]
            [clojure-ttt.ai :refer :all]))

(describe "Scoring end of the game"
  (it "Scores a tie as 0"
    (should= 0 (score-board ["x" "x" "o" "o" "o" "x" "x" "x" "o"] "x")))

  (it "Scores a win as 1"
    (should= 1.0 (score-board ["x" "x" "x" 4 5 6 7 8 9] "x")))

  (it "Scores a loss as -1"
    (should= -1.0 (score-board ["x" "x" "x" 4 5 6 7 8 9] "o"))))

(describe "AI making a move"
  (it "Gets square of highest rated move from hash of squares and scores"
    (should= 2 (max-value {1 1 2 100 3 3})))

  (it "Places piece on board according to highest-rated move"
    (with-redefs [minimax (fn [& _] ["x" "x" "x" 4 5 6 7 8 9])]
    (should= ["x" "x" "x" 4 5 6 7 8 9] (ai-move [1 "x" "x" 4 5 6 7 8 9] "x")))))

