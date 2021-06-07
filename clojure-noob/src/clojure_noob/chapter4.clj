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

(reduce * [1 2 3 4])
; => 24
(reduce * 2 [3 4])
; => 24
(reduce (fn [new-map [key val]]
          (assoc new-map key (inc val)))
        {}
        {:max 30 :min 10})
; => {:max 31, :min 11}
(map #(titleize (second %)) {:uncomfortable-thing "Winking"})
; => (map #(titleize (second %)) {:uncomfortable-thing "Winking"})
; second -> seq of map returns a (list of) vectors
(seq {:name "Bill Compton" :occupation "Dead mopey guy"})
; => ([:name "Bill Compton"] [:occupation "Dead mopey guy"])
(assoc {} :key1 "value" :key2 "another value")
; => {:key1 "value", :key2 "another value"}
(def my-map (assoc {} :key1 1 :key2 2))
my-map
; => {:key 1, :key2 2}

(def food-journal
  [{:month 1 :day 1 :human 5.3 :critter 2.3}
   {:month 1 :day 2 :human 5.1 :critter 2.0}
   {:month 2 :day 1 :human 4.9 :critter 2.1}
   {:month 2 :day 2 :human 5.0 :critter 2.5}
   {:month 3 :day 1 :human 4.2 :critter 3.3}
   {:month 3 :day 2 :human 4.0 :critter 3.8}
   {:month 4 :day 1 :human 3.7 :critter 3.9}
   {:month 4 :day 2 :human 3.7 :critter 3.6}])
(take-while #(< (:month %) 4)
            (drop-while #(< (:month %) 2) food-journal))
(drop-while #(< (:month %) 2)
            (take-while #(< (:month %) 4) food-journal))
(drop-while #(< (:month %) 2) food-journal)
; => ({:month 2, :day 1, :human 4.9, :critter 2.1} {:month 2, :day 2, :human 5.0, :critter 2.5} {:month 3, :day 1, :human 4.2, :critter 3.3} {:month 3, :day 2, :human 4.0, :critter 3.8} {:month 4, :day 1, :human 3.7, :critter 3.9} {:month 4, :day 2, :human 3.7, :critter 3.6})
(take-while #(< (:month %) 4) food-journal)
; => ({:month 1, :day 1, :human 5.3, :critter 2.3} {:month 1, :day 2, :human 5.1, :critter 2.0} {:month 2, :day 1, :human 4.9, :critter 2.1} {:month 2, :day 2, :human 5.0, :critter 2.5} {:month 3, :day 1, :human 4.2, :critter 3.3} {:month 3, :day 2, :human 4.0, :critter 3.8})

(some #(and (> (:critter %) 3) %) food-journal)
; => {:month 3, :day 1, :human 4.2, :critter 3.3}
(some #(and (> (:critter %) 5) %) food-journal)
; => nil
(some #(> (:critter %) 5) food-journal)
; => nil
(and 1 2)
; => 2
(and nil 2)
; => nil
(and false true)
; => false
(and true false)
; => false
(or 1 2)
; => 1

(concat [1 2] [3 4])
; => (1 2 3 4)
(concat ["a"] ["b"])
; => ("a" "b")
