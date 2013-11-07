(ns clojure-ttt.ai-spec
  (:require [speclj.core :refer :all]
            [clojure-ttt.board :refer :all]
            [clojure-ttt.ai :refer :all]))

(describe "Scoring end of the game"
  (it "Scores a tie as 0"
    (should= 0 (score-board ["x" "x" "o" "o" "o" "x" "x" "x" "o"] "x")))

  (it "Scores a win as 1"
    (should= 1.0 (score-board ["x" "x" "x" 4 5 6 7 8 9] "x"))
    (should= 1.0 (score-board ["o" "o" "o" 4 5 6 7 8 9] "o")))

  (it "Scores a loss as -1"
    (should= -1.0 (score-board ["x" "x" "x" 4 5 6 7 8 9] "o"))
    (should= -1.0 (score-board ["o" "o" "o" 4 5 6 7 8 9] "x")))
  
  (it "Does not score a game in progress"
    (should= nil (score-board [1 2 3 4 5 6 7 8 9] "x"))))

(describe "generate-one-level-deep"
  (it "generates a new level of boards"
    (should= [
              ["o" "x" 3 "o" "x" "o" "x" "o" "x"]
              [1 "x" "o" "o" "x" "o" "x" "o" "x"] 
             ] 
             (generate-one-level-deep "o" [1 "x" 3 "o" "x" "o" "x" "o" "x"]))))

(describe "AI making a move"

  (it "scores a possibility for a tie"
    (should= 0 (minimax [1 "x" "o" "o" "o" "x" "x" "x" "o"] true 1)))
          
  (it "scores a possibility for a win"
    (should= -0.5 (minimax [1 "o" "o" "x" 5 "x" "o" "x" 9] true 1)))

  (it "scores a possibility for a loss as -1"
    (should= -1.0 (minimax [ "x"  2  "o"
                                 4  "o"  6
                                "x" "x"  "x"] false 1)))
  

  (it "Gets square of highest rated move from hash of squares and scores"
    (should= 2 (max-value {1 1 2 100 3 3})))

  (it "Places piece on board according to highest-rated move"
    (should= ["x" "x" "x" "o" "x" "o" "o" "o" "x"] 
      (board-with-ai-move [1 "x" "x" "o" "x" "o" "o" "o" "x"])))

  (it "Returns a hash of squares with their respective scores"
    (should= {1 -1.0} (scores-with-moves [ 1  "x" "x" 
                                          "o" "x" "o" 
                                          "o" "o" "x"]))
    (should= {1 0} (scores-with-moves [ 1  "o" "x" 
                                       "x" "x" "o" 
                                       "o" "x" "o"]))
      )

  (it "Returns a hash of squares with their respective scores"
    (should= {1 1.0 8 -0.5} 
             (scores-with-moves [ 1  "x" "o" 
                                 "o" "x" "x" 
                                 "o"  8  "x"])))

  (it "Blocks a win"
    (should= 3 (ai-move ["x" "x" 3 
                          4  "o" 6 
                          7   8  9])))

  (it "Prefers win over not winning"
    (should= 3 (ai-move ["o" "o" 3 
                         "x" "x" 6 
                          7  "x" 9])))

  (it "moves to center after first move"
    (should= 5 (ai-move ["x" 2 3 
                          4  5 6 
                          7  8 9])))

  (it "ai-move blocks a fork"
    (should= 2 (ai-move 
              ["x"  2  3
                4  "o" 6
                7   8 "x"]))))
