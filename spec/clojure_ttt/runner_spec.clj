(ns clojure-ttt.runner_spec
  (:import [clojure_ttt.runner Human])
  (:require [speclj.core :refer :all]
            [clojure-ttt.runner :refer :all]
            [clojure-ttt.input-output :refer :all]))

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
      (:piece human))))

  (it "Makes a move with a human player"
    (with-redefs [println (fn [& _] "Please enter input.")
                  read-line (fn [& _] "2")]
    (should= [1 "x" 3 4 5 6 7 8 9]
      (take-turn (Human. "x") [1 2 3 4 5 6 7 8 9])))))
    
