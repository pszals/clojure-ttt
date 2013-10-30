(ns clojure-ttt.core)

  (defn place-piece [square marker board]
    (assoc board (- square 1) marker))   
 
  (defn open? [board square]
    (number? (nth board (- square 1))))

  (defn rows [board]
    (partition 3 board))

;  (defn generate-column [board starting-index] (let [width (int (Math/sqrt (count board)))]
;      (loop [start starting-index
;             column []]
;        (if (= (count column) width)
;          column
;          (recur (+ width start) (conj column start))))))
  
  (defn columns [board]
    (apply map list (rows board)))

  (defn three-of-a-kind? [row]
    (every? 
      (fn matches-first-in-row? [value] (= (first row) value))
      row)) 
  
  (defn row-winner? [board]
    (let [across (rows board)]
      (or (three-of-a-kind? (nth across 0)) (three-of-a-kind? (nth across 1)) (three-of-a-kind? (nth across 2)))))
