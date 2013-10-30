(ns clojure-ttt.board)

  (defn place-piece [square marker board]
    (assoc board (- square 1) marker))   
 
  (defn open? [board square]
    (number? (nth board (- square 1))))

  (defn rows [board]
    (partition 3 board))

  (defn columns [board]
    (apply map list (rows board)))

  (defn three-of-a-kind? [row]
    (every? 
      (fn matches-first-in-row? [value] (= (first row) value))
      row)) 
  
  (defn row-winner? [board]
    (let [across (rows board)]
      (or (three-of-a-kind? (nth across 0)) (three-of-a-kind? (nth across 1)) (three-of-a-kind? (nth across 2)))))

  (defn diagonals [board]
    (list (list (nth board 0) (nth board 4) (nth board 8))
      (list (nth board 2) (nth board 4) (nth board 6))))
  
  (defn winning-combos [board]
    (concat (rows board) (columns board) (diagonals board)))
    
