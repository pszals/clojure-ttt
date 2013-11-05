(ns clojure-ttt.players_spec
  (:require [speclj.core :refer :all]
            [clojure-ttt.players :refer :all]
            [clojure-ttt.input-output :refer :all]
            [clojure-ttt.ai :refer :all])
  (:import [clojure_ttt.players Human]
           [clojure_ttt.players Computer]))

(describe "Making a move"
  (it "Prompts and validates user input"
    (with-redefs [println (fn [& _] "Please enter input.")
                  read-line (fn [& _] "2")]
    (should= 2 (human-move [1 2 3 4 5 6 7 8 9]))))
  
  (it "Returns marker type of player"
    (should= "x" (marker (Human. "x"))))

  (it "Creates a human player"
    (let [human (create-player "x")]
    (should= "x"
      (:piece human))
    (should= clojure_ttt.players.Human (class human))))

  (it "Creates a computer player"
    (let [computer (create-player "o")]
    (should= "o"
      (:piece computer))
    (should= clojure_ttt.players.Computer (class computer))))

  (it "Makes a move with a human player"
    (with-redefs [println (fn [& _] "Please enter input.")
                  read-line (fn [& _] "2")]
    (should= [1 "x" 3 4 5 6 7 8 9]
      (take-turn (Human. "x") [1 2 3 4 5 6 7 8 9]))))

  (it "Makes a move with a computer player"
    (with-redefs [board-with-ai-move (fn [& _] ["x" "o" 3 4 "o" 6 7 8 "x"])]
    (should= ["x" "o" 3 4 "o" 6 7 8 "x"]
      (take-turn (Computer. "o") ["x" 2 3 4 "o" 6 7 8 "x"])))))
