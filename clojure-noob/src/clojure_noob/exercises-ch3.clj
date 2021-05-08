(ns clojure-noob.core
  (:gen-class))

; smoke test function of setup
(+ 1 2 3)

; Exercise 1
; Use the str, vector, list, hash-map, and hash-set functions.

(str)
; => ""
(str 1)
; => "1"
(str 1 "a")
; => "1a"
(str 1 " " "a")
; => "1 a"
(str 1 'a "a")
; => "1aa"
(str 1 'a' "a")
; => "1a'a"
(str 1 [2 3])
; => "1[2 3]"
(str 1 (2 3))
; ClassCastException class java.lang.Long cannot be cast to class clojure.lang.IFn (java.lang.Long is in module java.base of loader 'bootstrap'; clojure.lang.IFn is in unnamed module of loader 'app')  clojure-noob.core/eval1442 (form-init 9121953781140959953.clj:21)
(str 1 '(2 3))
; => "1(2 3)"
(str 1 {:2 "three"})
; => "1{:2 \"three\"}"
(str 1 {:2 3})
; => "1{:2 3}"

(vector)
; => []
(vector 1)
; => [1]
(vector 1 "a")
; => [1 "a"]
(vector 1 [2 3])
; => [1 [2 3]]
(vector 1 (2 3))
; ClassCastException class java.lang.Long cannot be cast to class clojure.lang.IFn 
(vector 1 '(2 3))
; => [1 (2 3)]
(vector 1 {:2 3})
; => [1 {:2 3}]

(list)
; => ()
(list 1)
; => (1)
(list 1 "a")
; => (1 "a")
(list 1 [2 3])
; => (1 [2 3])
(list 1 (2 3))
; ClassCastException class java.lang.Long cannot be cast to class clojure.lang.IFn
(list 1 '(2 3))
; => (1 (2 3))
(list 1 {:2 3})
; => (1 {:2 3})

(hash-map)
; => {}
(hash-map 1)
; IllegalArgumentException No value supplied for key: 1
(hash-map 1 2)
; => {1 2}
(hash-map :1 2)
; => {:1 2}
(hash-map "a" "b")
; => {"a" "b"}
(def my-hash (hash-map 1 2))
my-hash
; => {1 2}
(my-hash 1)
; => 2
(get my-hash 1)
; => 2
(1 my-hash)
; ClassCastException class java.lang.Long cannot be cast to class clojure.lang.IFn
(def my-other-hash (hash-map :1 2))
my-other-hash
; => {:1 2}
(my-other-hash :1)
; => 2
(:1 my-other-hash)
; => 2
(hash-map :1 [2 3])
; => {:1 [2 3]}
(hash-map :1 {:2 3})
; => {:1 {:2 3}}
(hash-map :1 (2 3))
; ClassCastException class java.lang.Long cannot be cast to class clojure.lang.IF

(hash-set)
; => #{}
(hash-set 1)
; => #{1}
(hash-set 1 2)
; => #{1 2}
(hash-set 1 1 2)
; => #{1 2} -> unique values
(hash-set 1 "a")
; => #{1 "a"}
(hash-set 1 [2 3])
; => #{[2 3] 1}
(hash-set 1 [2 3] [2 3])
; => #{[2 3] 1}
(hash-set 1 (2 3))
; ClassCastException class java.lang.Long cannot be cast to class clojure.lang.IFn
(hash-set 1 {:2 3})
; => #{1 {:2 3}}
(hash-set 1 {:2 3} {:2 3})
; => #{1 {:2 3}}
(hash-set 1 [2 ["a"]] [2 ["a"]])
; => #{1 [2 ["a"]]}

; Exercise 2
; Write a function that takes a number and adds 100 to it.

(defn plus-100
  " add 100 to a number; return 100 if no argument given"
  [number]
  (+ 100 number)
  )
(plus-100 0)
; => 100
(plus-100 1)
; => 101
(plus-100 -10)
; => 90
(plus-100 1.3)
; => 101.3
(plus-100 (+ 1 9))
; => 110
(plus-100)
; ArityException Wrong number of args (0) passed to: core/plus-100

(defn plus-100-multi-artity
  ([]
   100)
  ([number]
   (+ 100 number)
   )
  )
(plus-100-multi-artity)
; => 100
(plus-100-multi-artity 1)
; => 101

(defn plus-100-messed-up
  ([]
   "one hundred")
  ([number]
   (+ 100 number)
   )
  )
(plus-100-messed-up)
; => "one hundred"
(plus-100-messed-up 1)
; => 101

(defn plus-100-arity-overloading
  ([number]
   (+ 100 number))
  ([]
   (plus-100-arity-overloading 0)
   )
  )
(plus-100-arity-overloading)
; => 100
(plus-100-arity-overloading 0)
; => 100
(plus-100-arity-overloading 1)
; => 101


; Exercise 3
; Write a function, dec-maker, that works exactly like the function inc-maker except with subtraction
; (def dec9 (dec-maker 9))
; (dec9 10)
; => 1
(defn dec-maker
  [dec-by]
  #(- % dec-by))
(def dec9 (dec-maker 9))
(type dec9)
; => clojure_noob.core$dec_maker$fn__1458
(dec9 10)
; => 1

(type 10)
; => java.lang.Long

(def def-dec9 (fn [num] (- num 9)))
; => #'clojure-noob.core/def-dec9
(type def-dec9)
; => clojure_noob.core$def_dec9
(def-dec9 10)
; => 1

( #(+ 10 %1 %2) 20 30)
; => 60

(- 10 1 2)
; => 7
(- 10 1 -2)
; => 11
(- 10 -1 2)
; => 9

; Exercises 4-5-6 require use of functions not covered yet in the book


; Exercise 4
; Write a function, mapset, that works like map except the return value is a set:
; (mapset inc [1 1 2 2])
; => #{2 3}
(map inc [1 1 2 2])
; => (2 2 3 3)
(inc #{1 2})
; ClassCastException class clojure.lang.PersistentHashSet cannot be cast to class java.lang.Number
(set (1 2))
; ClassCastException class java.lang.Long cannot be cast to class clojure.lang.IFn
(set '(1 2))
; => #{1 2}
(into #{} [1 2])
; => #{1 2}
(into #{} (1 2))
; ClassCastException class java.lang.Long cannot be cast to class clojure.lang.IFn
(into #{} '(1 2))
; => #{1 2}

(defn mapset-inc
  [numbers]
  (loop [remaining-numbers numbers
         result #{}]
    (if (empty? remaining-numbers)
      result
      (let [[part & remaining] remaining-numbers]
        (recur remaining
               (into result (inc part)))))))
(mapset-inc [1 1 2 2])
; IllegalArgumentException Don't know how to create ISeq from: java.lang.Long

; Exercise 5
; Create a function that’s similar to symmetrize-body-parts except that it has to work with weird space aliens with radial symmetry. Instead of two eyes, arms, legs, and so on, they have five.

; Exercise 6
; Create a function that generalizes symmetrize-body-parts and the function you created in Exercise 5. The new function should take a collection of body parts and the number of matching body parts to add.
; If you’re completely new to Lisp languages and functional programming, it probably won’t be obvious how to do this. If you get stuck, just move on to the next chapter and revisit the problem later
