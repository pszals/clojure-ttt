(ns clojure-ttt.runner_spec
  (:require [speclj.core :refer :all]
            [clojure-ttt.runner :refer :all]
            [clojure-ttt.players :refer :all]
            [clojure-ttt.input-output :refer :all]))

(describe "Game Cycle"
  (it "Finishes the game when there is a move left to win"
    (with-redefs [println (fn [& _] "Player O Wins")
                  take-turn (fn [& _] "2")]
      (should= "Player O Wins" (play-game 
                         ["x"  2  "x"
                          "o" "o"  6
                          "x" "o" "o"])))))
      
    
