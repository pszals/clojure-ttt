(ns clojure-ttt.board)

  (defn place-piece [square piece board]
    (assoc board (dec square) piece))   
 
  (defn open? [board square]
    (number? (nth board (- square 1))))

  (defn rows [board]
    (partition 3 board))

  (defn columns [board]
    (apply map list (rows board)))

  (defn diagonals [board]
    (let [row-1 (first (rows board))
          row-2 (second (rows board))
          row-3 (last (rows board))]
      [[(first row-1) (second row-2) (last row-3)]
       [(last row-1) (second row-2) (first row-3)]]))
  
  (defn three-of-a-kind? [row]
    (every? 
      (fn matches-first-in-row? [value] (= (first row) value))
      row)) 
  
  (defn row-winner? [board]
    (let [across (rows board)]
      (or (three-of-a-kind? (nth across 0)) (three-of-a-kind? (nth across 1)) (three-of-a-kind? (nth across 2)))))

  (defn winning-combos [board]
    (concat (rows board) (columns board) (diagonals board)))

  (defn winner? [board]
    (>= (count 
      (filter #(true? %) (map three-of-a-kind? (winning-combos board)))) 
      1))

  (defn list-empty-squares [board]
    (filter number? board))

  (defn empty-squares [board]
    (count (list-empty-squares board)))
  
  (defn full? [board]
    (= 0 (empty-squares board)))

  (defn game-over? [board]
    (or (full? board) (winner? board)))

  (defn piece-to-play [board]
    (if (= 0 (mod (empty-squares board) 2))
      "o"
      "x"))

  (defn opponent-piece [board]
    (if (= 0 (mod (empty-squares board) 2))
      "x"
      "o"))

  (defn three-of-a-kind? [row]
    (every? 
      (fn matches-first-in-row? [value] (= (first row) value))
      row)) 

  (defn winning-piece [board]
    (first (first (filter three-of-a-kind? (winning-combos board)))))
