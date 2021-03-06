(ns clojure-noob.core
  (:gen-class))

;; examples from the book - or
; returns first truthy or last value
(or false nil :large_i_mean_venti :why_cant_i_just_say_large)
; => :large_i_mean_venti

(or (= 0 1) (= "yes" "no"))
; => false

(or nil)
; => nil

;; examples from the book - and
; returns first falsy or last value (described differently in book)
(and :free_wifi :hot_coffee)
; => :hot_coffee

(and :feelin_super_cool nil false)
; => nil

(defn cond1 [inp] (if (< inp 5) "yep"))
(defn cond2 [inp] (if (integer? inp) true false))

(or (cond1 3) (cond2 7))
; => yep
(or (cond1 7) (cond2 7))
; => true
(or (cond1 7) (cond2 "seven"))
; => false

; (defn is_int [inp] (if (integer? inp) true nil))
; (defn mult2 [input] (* input 2))
; (defn mult2_if_int [input] (and (is_int input) (mult2 input)))
(defn mult2_if_int [input] (and (if (integer? input) true) (* input 2)))
; (defn mult2_if_int [input] (if (integer? input) true) (* input 2))

(mult2_if_int 4)
; => 8
(mult2_if_int "four")
; => nil

(defn mult3_if_int [input] (if (integer? input) (* input 3)))
(mult3_if_int 4)
; => 12
(mult3_if_int "four")
; => nil

(defn mult4_if_int [input] (cond
                             (integer? input) (* input 4)
                             :else nil))
(mult4_if_int 2) ; => 8
(mult4_if_int "two") ; => nil

; same thing as and but with or
; (or nil "crazy default value")
(or (mult2_if_int 4) "that was not a number")
(or (mult2_if_int "four") "that was not a number")

(nil? "")
(false? "")
(true? "")
(true? "a")
(if "" "yes" "no")
(not "foo")  ; => false

;; questions
; Why not use the if version? For switch-like forms?
; Why change string to nil?

;; my stuff
; and last value is first falsy
(and true true)
; => true
(and true :tree)
; => :tree
(and true false)
; => false
(and true nil)
; => nil
(and)
; => true

; or last value is frist truthy
(or false true)
; => true
(or false false :falcon)
; => :falcon
(or false false false)
; => false
(or false false nil)
; => nil
(or)
; => nil


; or
(or (> 7 3) (< 7 12))
; => true

(or false false :at_least_truthy)
; => :at_least_truthy

; or returning first truthy
(or true :is_truthy)
; => true
(or :is_truthy true)
; => :is_truthy
; would you do this though?
(or (= 1 1) false)
; => true

; or returning last value
; nil and false are only to represent logical falsiness
(or false false)
; => false
(or false nil)
; => nil


(or (if (= 1 1) :true) false)
