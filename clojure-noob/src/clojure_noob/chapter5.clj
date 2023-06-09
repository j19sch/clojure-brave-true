(ns clojure-noob.core
  (:gen-class))

(+ 1 2)
; => 3

(defn sum
      ([vals] (sum vals 0))
      ([vals accumulating-total]
       (if (empty? vals)
         accumulating-total
         (sum (rest vals) (+ (first vals) accumulating-total)))))

(sum [39 5 1])
; => 45

(defn sum-recur
  ([vals] (sum vals 0))
  ([vals accumulating-total]
   (if (empty? vals)
     accumulating-total
     (recur (rest vals) (+ (first vals) accumulating-total)))))

(sum-recur [39 5 1])
; => 45

(require '[clojure.string :as s])
(defn clean
  [text]
  (s/replace (s/trim text) #"lol" "LOL"))

(clean "my boa constrictor is so sassy lol!  ")
; => "my boa constrictor is so sassy LOL!"

((comp inc *) 2 3)
; => 7
(inc (* 2 3))
; => 7

(def character
  {:name "Smooched McCutes"
         :attributes {:intelligence 10}})
(def c-int (comp :intelligence :attributes))

(c-int character)
; => 10

(defn spell-slots
  [char]
  (int (inc (/ (c-int char) 2))))

(spell-slots character)
; => 6

(def spell-slots-comp (comp int inc #(/ % 2) c-int))
(spell-slots-comp character)
; => 6
((comp int inc #(/ % 2) c-int) character)
; => 6

(defn two-comp
  [f g]
  (fn [& args]
    (f (apply g args))))
((two-comp inc *) 2 3)
; => 7

