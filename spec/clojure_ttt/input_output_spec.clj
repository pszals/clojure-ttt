(ns clojure-ttt.input-output-spec
  (:require [speclj.core :refer :all]
            [clojure-ttt.input-output :refer :all]))

(describe "Prompt for a move"
  (it "Tells whose turn it is to play"
    (should= "It's player x's turn" (prompt-move "x")))

  (it "Complains about bad input"
    (should= "Please enter a number between 1 and 9" (invalid))))

(describe "End of game messages"
  (it "Tells who won"
    (should= "Player x wins!" (game-won-by "x")))

  (it "Announces a tie"
    (should= "Tie game!" (tie-game))))

(describe "Output to console"
  (it "Prints a message to console"
    (should= "message\n" (with-out-str (output "message"))))

  (it "Prompts for input"
    (with-redefs [println (fn [& _] "Please enter input.")
                  read-line (fn [& _] "2")]
      (should= "2" (input "Please enter input."))))) 

(describe "Displaying board"
  (it "Puts pipes between squares in a row"
    (should= ["1 | 2 | 3"] (row-pipes [1 2 3])))

  (it "Puts pipes between squares in all rows"
    (should= [["1 | 2 | 3"] ["4 | 5 | 6"] ["7 | 8 | 9"]] 
      (board-pipes [1 2 3 4 5 6 7 8 9])))

  (it "Outputs each row on a new line"
    (should= "1 | 2 | 3\n---------\n4 | 5 | 6\n---------\n7 | 8 | 9" 
      (new-lines [1 2 3 4 5 6 7 8 9]))))

(run-specs)