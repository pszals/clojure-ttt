(ns clojure-ttt.core)

  (defn place-piece [square marker board]
    (assoc board (- square 1) marker))   
 
  (defn open? [board square]
    (number? (nth board (- square 1))))

  (defn rows [board]
    (partition 3 board))
  
  (defn three-of-a-kind? [row]
    (= (nth row 0) (nth row 1) (nth row 2)))
  
  (defn row-winner? [board]
    (let [across (rows board)]
      (or (three-of-a-kind? (nth across 0)) (three-of-a-kind? (nth across 1)) (three-of-a-kind? (nth across 2)))))

  (defn columns [board]
    [[1 4 7] [2 5 8] [3 6 9]])
