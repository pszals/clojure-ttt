(ns clojure-ttt.ai-spec
  (:require [speclj.core :refer :all]
            [clojure-ttt.board :refer :all]
            [clojure-ttt.ai :refer :all]))

(describe "Scoring end of the game"
  (it "Scores a tie as 0"
    (should= 0 (score-board ["x" "x" "o" "o" "o" "x" "x" "x" "o"] "x")))

  (it "Scores a win as 1"
    (should= 1 (score-board ["x" "x" "x" 4 5 6 7 8 9] "x")))

  (it "Scores a loss as -1"
    (should= -1 (score-board ["x" "x" "x" 4 5 6 7 8 9] "o"))))
