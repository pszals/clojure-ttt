(ns clojure-ttt.core-spec
  (:require [speclj.core :refer :all]
            [clojure-ttt.core :refer :all]))

(describe "Truth"
  (it "is true"
    (should true))
  
  (it "is not false"
    (should-not false)))

(describe "Addition of two numbers"
  (it "Adds 2 and 2"
    (should= 4 (add-two 2 2))))

(describe "Placing a piece on the board"
  (it "Modifies board for a given square"
    (should= '("x" 2 3 4 5 6 7 8 9) (place-piece 1 "x" '(1 2 3 4 5 6 7 8 9)))))

(run-specs)
