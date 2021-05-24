(ns clojure-noob.core
  (:gen-class))

(+ 1 2)
; => 3

(map str ["a" "b" "c"])
; => ("a" "b" "c")
(map str ["a" "b" "c"] ["A" "B" "C"])
; => ("aA" "bB" "cC")
(map str ["a" "b"] ["A" "B"] [1 2])
; => ("aA1" "bB2")
(map str ["a"] ["A" "B"])
; => ("aA")
(map str ["a" "b"] ["A"])
; => ("aA")
(map str ["a"] [])
; => ()

(str 1 2 3)
; => "123"
(+ 1 2 3)
; => 6
(defn strnsum
  [numbers]
  (map #(% numbers) [str +])
  )
(strnsum [1 2 3])
; (map [1 2 3] [str +])
; does not work

(def sum #(reduce + %))
(def avg #(/ (sum %) (count %)))
(defn stats
  [numbers]
  (map #(% numbers) [sum count avg]))
(stats [3 4 10])
; => (17 3 17/3)
(stats [80 1 44 13 6])
; => (144 5 144/5)

(def strn #(reduce str %))
(defn strnsum2
  [numbers]
  (map #(% numbers) [strn sum]))
(strnsum2 [1 2 3])
; => ("123" 6)

(reduce + [1 2 3])
; => 6
(defn sumfn
  [numbers]
  (reduce + numbers))
(sumfn [1 2 3])
; => 6

(defn stats2
  [numbers]
  (map #(% numbers) [sumfn count avg]))
(stats2 [3 4 10])
; => (17 3 17/3)

(map #(str %) [1 2 3])
; => ("1" "2" "3")
(map #(% 1 2 3) [str +])
; => ("123" 6)
