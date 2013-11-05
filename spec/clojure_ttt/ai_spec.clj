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

(describe "generate-one-level-deep"
  (it "generates a new level of boards"
    (should= [
              ["o" "x" 3 "o" "x" "o" "x" "o" "x"]
              [1 "x" "o" "o" "x" "o" "x" "o" "x"] 
             ] 
             (generate-one-level-deep [1 "x" 3 "o" "x" "o" "x" "o" "x"]))))


(describe "AI making a move"
  (it "Gets square of highest rated move from hash of squares and scores"
    (should= 2 (max-value {1 1 2 100 3 3})))

  (it "Places piece on board according to highest-rated move"
;    (with-redefs [minimax (fn [& _] 1)]
    (should= ["x" "x" "x" "o" "x" "o" "o" "o" "x"] (board-with-ai-move [1 "x" "x" "o" "x" "o" "o" "o" "x"] "x"))))

