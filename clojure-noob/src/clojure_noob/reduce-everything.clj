(ns clojure-noob.core
   (:gen-class))

; If you want an exercise that will really blow your hair back, try implementing map using reduce,
; and then do the same for filter and some after you read about them later in this chapter.

(map inc [1 2 3])
; => (2 3 4)

(reduce + [1 2 3])
; => 6
(reduce + 1 [2 3])
; => 6
(reduce (fn [vect number]
          (conj vect (inc number)))
        []
        [1 2 3])
; => [2 3 4]

(reduce (fn [vect number]
          (conj vect (inc number)))
        ()
        [1 2 3])
; => (4 3 2)
; explained in docs: conj of list is done at the beginning

(conj () 1)
; => (1)
(conj () [1 2])
; => ([1 2])
(conj () 1 2)
; => (2 1)
(cons () [1 2])
; => (() 1 2)
(concat () [1 2])
; => (1 2)

(reduce (fn [vect number]
          (concat vect [(inc number)]))
        ()
        [1 2 3])
; => (2 3 4)

(defn my-map [func inp]
  (reduce (fn [vect number]
            (concat vect [(func number)]))
          ()
          inp)
  )
(my-map inc [1 2 3])
; => (2 3 4)
(my-map dec [4 5 6])
; => (3 4 5)
