(ns clojure-noob.core
  (:gen-class))

(def the-name "Chewbacca")
; (str the-name)  ; does not work without the str
the-name

(def my-map {:a 0 :b {:c "ho hum"}})
; (my-map)  ; does not work, don't know how to fix
my-map
; {:a 0, :b {:c "ho hum"}}

(def my-other-map (hash-map :a 100 :b 101 :c 102))
my-other-map
; (my-other-map) does not work, the one above does with cp$ instead of cpp
; (my-other-map)

"asdasd"
("asdasd")

(get my-map :a)
; => 0

(my-map :a)
; => 0

(:a my-map)
; => 0
(:d my-map "not in here")
; => "not in here"

(get my-map :d)
; => nil
(get my-map :d "not in here")
; => "not in here"

(get-in my-map [:b :c])
; => "ho hum"

; returning functions stuff
(defn inc4 [number] (+ number 4))

(inc4 7)

(defn inc-maker
  "Create a custom incrementor"
  [inc-by]
  #(+ % inc-by))

(def inc3 (inc-maker 3))

(inc3 7)
; 10

; what if there is more scope in the parent function?
; the stuff below does not work: surprise in this context

(def suprise 1)

(defn weird-inc-maker
  [inc-by]
  (println surprise)
  ; #(+ % surprise inc-by)
  )
