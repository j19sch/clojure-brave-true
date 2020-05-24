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
