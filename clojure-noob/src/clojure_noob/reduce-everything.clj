(ns clojure-noob.core
   (:gen-class))

; reduce without coll
(reduce print [])
; => nil

; reduce without col but with coll
(reduce print 1 [])
; => 1

; reduce with single item in coll (print 3) ;=> 3nil (reduce print [3])
; => 3
(reduce print [3 4])
; => 3 4nil

; If you want an exercise that will really blow your hair back, try implementing map using reduce,
; and then do the same for filter and some after you read about them later in this chapter.

(map inc [1 2 3])
; => (2 3 4)

(map dec [4 5 6])
; => (3 4 5)

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


; filter as reduce: loop through input, add to output if condition is true?
(def numbers [1 2 3 4 5])

(filter #(< % 4) numbers)
; => (1 2 3)

(defn my-simple-filter [inp]
  (reduce (fn [vect numb]
            (if (< numb 4)
              (concat vect [numb])
              vect))
          ()
          inp
          )
  )

(my-simple-filter [1 2 3 4 5])
; => (1 2 3)

(if (even? 5)
  "yes"
  "no"
  )

(my-simple-filter numbers)
;=> (1 2 3)


; https://stackoverflow.com/a/26560579
(defn rec [f numbers acc]
  (reduce f acc numbers))

(rec + [1 2 3] 0)
; => 6

(defn plus [a b] (+ a b))
(plus 2 3)

(rec plus [1 2 3] 0)
;=> 6

(defn red-func-lt4 [vect numb]
  (if (< numb 4)
    (concat vect [numb])
    vect))

(red-func-lt4 [1 2] 3)
;=> (1 2 3)

(reduce red-func-lt4 () numbers)
;=> (1 2 3 4 5)
;=> (1 2 3) when not anon function in red-funct-lt4

(defn my-filter-2 [func inp]
  (reduce func () inp
          )
  )

(my-filter-2 red-func-lt4 numbers)
; => (1 2 3)

(defn red-func [f vect numb]
  (if f
    (concat vect [numb])
    vect))

(red-func lt4 [1 2] 3)
;=> (1 2 3)

(defn my-filter-3 [func inp]
  (reduce
    #(if func
       (concat %1 [%2])
       %1)
    ()
    inp
    )
  )

(my-filter-3 #(< % 4) [1 2 3 4 5])
;=> (1 2 3 4 5)

(defn my-filter [func inp]
  (reduce (fn [vect numb]
            (if func
              (concat vect [numb])
              vect))
          ()
          inp
  )
)

(defn my-filter [func inp]
  ((defn f [vect numb]
     (if func
       (concat vect [numb])
       vect))
     (reduce f () inp)
   )
  )

(defn two-lines [a b]
  (print a)
  (print b)
  )
(two-lines "one" "two")
;=> onetwonil

(defn lt4 [anumb] (< anumb 4))
(lt4 3)
;=> true
(lt4 4)
;=> false

(my-filter lt4 numbers)
;=> (1 2 3 4 5)

(my-filter #(< % 4) numbers)
;=> don't know how to create ISeq
;=> (1 2 3 4 5) -> not sure why it did this -> got it back after putting func and inp in the right place
; brackets and vim-sexp are messing with me, more sluprage and barfage!
; hypothesis: func is evaluated not executed

; filter demonstrated in reduce explanation
(reduce (fn [vect numb]
          (if (< numb 4)
            (concat vect [numb])
            vect))
        () 
        [1 2 3 4 5])
;=> (1 2 3)
;=> to vector is easier than list: conj, no [] around numb, val []


;=> some as reduce
(some #(< % 5) [1 2 3 4 5])
(some #(< % 5) [2 3 4 5])
;=> true -> why true and not 1?
(some #(> % 5) [1 2 3 4 5])
;=> nil

(reduce (fn [vect numb]
          (if (< numb 5
                 if not (empty? vect) )
            )))
