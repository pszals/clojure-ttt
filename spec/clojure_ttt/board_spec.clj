(ns clojure-ttt.board-spec
  (:require [speclj.core :refer :all]
            [clojure-ttt.board :refer :all]))

(describe "Placing a piece on the board"
  (it "Modifies board for first square"
    (should= ["x" 2 3 4 5 6 7 8 9] (place-piece 1 "x" [1 2 3 4 5 6 7 8 9]))) 

  (it "Modifies board for any square"
    (should= [1 "x" 3 4 5 6 7 8 9] (place-piece 2 "x" [1 2 3 4 5 6 7 8 9]))))

(describe "Validating a move"
  (it "Checks if square is occupied"
    (should= false (open? ["x"] 1)))

  (it "Checks if square is not occupied"
    (should= true (open? [1] 1))))

(describe "Finding a winner"
  (it "Checks first row for lack of three of a kind"
    (should= false (row-winner? [1 2 3 4 5 6 7 8 9])))
  
  (it "Returns true if three elements are all the same"
    (should= true (three-of-a-kind? ["x" "x" "x"])))

  (it "Returns false if three elements are not all the same"
    (should= false (three-of-a-kind? ["x" "x" 3])))

  (it "Checks first row for three of a kind"
    (should= true (row-winner? ["x" "x" "x" 4 5 6 7 8 9]))) 

  (it "Checks second row for three of a kind"
    (should= true (row-winner? [1 2 3 "x" "x" "x" 7 8 9]))))

(describe "Gathering winning combinations"
  (it "Gets rows from board"
    (should= [[1 2 3] [4 5 6] [7 8 9]] (rows [1 2 3 4 5 6 7 8 9])))
  
  (it "Gets columns from board"
    (should= [[1 4 7] [2 5 8] [3 6 9]] (columns [1 2 3 4 5 6 7 8 9])))
  
  (it "Gets diagonals from board"
    (should= [[1 5 9] [3 5 7]] (diagonals [1 2 3 4 5 6 7 8 9])))
          
  (it "Gathers all winning combinations"
    (should= [[1 2 3] [4 5 6] [7 8 9]
              [1 4 7] [2 5 8] [3 6 9]
              [1 5 9] [3 5 7]] (winning-combos [1 2 3 4 5 6 7 8 9]))))

(describe "Game-over situations"
  (it "Returns true if there are three in a row"
    (should= true (winner? ["x" "x" "x" 4 5 6 7 8 9])))

  (it "Returns false if there are not three in a row"
    (should= false (winner? ["x" "o" "x" 4 5 6 7 8 9])))

  (it "Returns true if board is full"
    (should= true (full? ["x" "x" "x" "x" "x" "x" "x" "x" "x"])))

  (it "Returns false if board is not full"
    (should= true (full? ["x" "x" "3" "x" "x" "x" "x" "x" "x"])))

  (it "Returns true if winner or full board"
    (should= true (game-over? ["x" "x" "x" 4 5 6 7 8 9])))

  (it "Returns false if no winner or no full board"
    (should= false (game-over? ["x" "x" "3" 4 5 6 7 8 9])))

  (it "Returns winning piece if three in a row"
    (should= "x" (winning-piece ["x" "x" "x" 4 5 6 7 8 9]))))

(describe "Knowing which piece is to play"
  (it "Returns 'x' when board is empty"
    (should= "x" (piece-to-play [1 2 3 4 5 6 7 8 9])))

  (it "Returns 'o' when board has one space taken"
    (should= "o" (piece-to-play [1 2 3 4 5 "x" 7 8 9]))))

(run-specs)
