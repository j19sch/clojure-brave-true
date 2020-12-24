(ns clojure-noob.core
  (:gen-class))

(def asym-hobbit-body-parts [{:name "head" :size 3}
                             {:name "left-eye" :size 1}
                             {:name "left-ear" :size 1}
                             {:name "mouth" :size 1}
                             {:name "nose" :size 1}
                             {:name "neck" :size 2}
                             {:name "left-shoulder" :size 3}
                             {:name "left-upper-arm" :size 3}
                             {:name "chest" :size 10}
                             {:name "back" :size 10}
                             {:name "left-forearm" :size 3}
                             {:name "abdomen" :size 6}
                             {:name "left-kidney" :size 1}
                             {:name "left-hand" :size 2}
                             {:name "left-knee" :size 2}
                             {:name "left-thigh" :size 4}
                             {:name "left-lower-leg" :size 3}
                             {:name "left-achilles" :size 1}
                             {:name "left-foot" :size 2}])

; asym-hobbit-body-parts

(defn matching-part
  [part]
  {:name (clojure.string/replace (:name part) #"^left-" "right-")
   :size (:size part)})

; (matching-part {:name "elbow" :size 1})
; (matching-part {:name "left-elbow" :size 1})
; (matching-part {:name "right-elbow" :size 1})

(defn symmetrize-body-parts
  "Expects a seq of maps that have a :name and :size"
  [asym-body-parts]
  (loop [remaining-asym-parts asym-body-parts final-body-parts []]
    (if (empty? remaining-asym-parts)
      final-body-parts
      (let [[part & remaining] remaining-asym-parts]
            (recur remaining
                   (into final-body-parts
                         (set [part (matching-part part)])))))))

(into [1 2] (set [3 3]))
; => [1 2 3]

(symmetrize-body-parts asym-hobbit-body-parts)

; (re-find #"^left-" "left-eye")
; (re-find #"^left-" "cleft-chin")

; (reduce + [1 2 3 4])
; (reduce + 15 [1 2 3 4])

(defn better-symmetrize-body-parts
  "Expects a seq of maps that have a :name and :size"
  [asym-body-parts]
  (reduce (fn [final-body-parts part]
               (into final-body-parts (set [part (matching-part part)])))
               []
               asym-body-parts))

(into [] [1 2 3] )
; => [1 2 3]
(conj [] [1 2 3] )
; => [[1 2 3]]
(into [9 8] [1 2 3] )
; => [9 8 1 2 3]
(conj [9 8] [1 2 3] )
; => [9 8 [1 2 3]]
(into [1 2 3] [])
; => [1 2 3]
(conj [1 2 3] [])
; => [1 2 3 []]
(into [1 2 3] 4)
; IllegalArgumentException Don't know how to create ISeq from: java.lang.Long  clojure.lang.RT.seqFrom (RT.java:542)
(conj [1 2 3] 4)
; [1 2 3 4]
(into [{:name "my-arm"}] [{:name "left-head"} {:name "right-head"}])
; => [{:name "my-arm"} {:name "left-head"} {:name "right-head"}]
(conj [{:name "my-arm"}] [{:name "left-head"} {:name "right-head"}])
; => [{:name "my-arm"} [{:name "left-head"} {:name "right-head"}]]

(better-symmetrize-body-parts asym-hobbit-body-parts)

(defn hit
  [asym-body-parts]
  (let [sym-parts (better-symmetrize-body-parts asym-body-parts)
        body-part-size-sum (reduce + (map :size sym-parts))
        target (rand body-part-size-sum)]
        (loop [[part & remaining] sym-parts
               accumulated-size (:size part)]
          (if (> accumulated-size target)
            part
            (recur remaining (+ accumulated-size (:size (first remaining))))))))

; (rand)
; (rand 5)

(hit asym-hobbit-body-parts)

(defn target-hit
  [asym-body-parts target]
  (let [sym-parts (better-symmetrize-body-parts asym-body-parts)]
        (loop [[part & remaining] sym-parts
               accumulated-size (:size part)]
          (if (> accumulated-size target)
            part
            (recur remaining (+ accumulated-size (:size (first remaining))))))))

(target-hit asym-hobbit-body-parts 0)  ; head
(target-hit asym-hobbit-body-parts 2)  ; head
(target-hit asym-hobbit-body-parts 3)  ; left-eye
(target-hit asym-hobbit-body-parts 84) ; left-foot, note: right before left in this script
(target-hit asym-hobbit-body-parts 85) ; NullPointerException

(defn looper
  [stuffs]
  (loop [the-stuffs stuffs
         the-stuffs-inc []]
    (if (empty? the-stuffs)
      the-stuffs-inc
      (let [[the-stuff & the-stuffz] the-stuffs]
        (recur the-stuffz (conj the-stuffs-inc (+ 1 the-stuff)))))))  ; had into instead of conj,does not work

(defn other-looper
  [stuffs]
  (loop [the-stuffs stuffs
         the-stuffs-inc []]
    (if (empty? the-stuffs)
      the-stuffs-inc
      (let [[the-stuff & the-stuffz] the-stuffs]
        (recur the-stuffz (conj the-stuffs-inc the-stuff ))))))

(other-looper [])  ; []]
(other-looper [1 2 3])  ; [1 2 3]
(other-looper [{:value 1} {:value 2} {:value 3}])  ; [{:value 1} {:value 2} {:value 3}]

(looper [])  ; => []
(looper [1 2 3])
; Don't know how to create ISeq -> fixed!
(looper [[1] [2] [3]])
; ClassCastException class clojure.lang.PersistentVector cannot be cast to class java.lang.Number (clojure.lang.PersistentVector is in unnamed module of loader 'app';
;  java.lang.Number is in module java.base of loader 'bootstrap')  clojure.l ang.Numbers.add (Numbers.java:128)
(looper 1 2 3)
; ArityException Wrong number of args (3) passed to: core/looper
(def the-input [1 2 3])
(looper the-input)
; Don't know how to create ISeq -> fixed


(defn set-looper
  [stuffs]
  (loop [the-stuffs stuffs
         the-stuffs-inc []]
    (if (empty? the-stuffs)
      the-stuffs-inc
      (let [[the-stuff & the-stuffz] the-stuffs]
        (recur the-stuffz (into the-stuffs-inc [(+ 1 the-stuff)]))))))  ; had into instead of conj,does not work
(set-looper [])
; []
(set-looper [1 2 3])
; [2 3 4]

(conj [1] [2])
; => [1 [2]]
(conj [1] 2)
; => [1 2]
(into [1] [2])
; => [1 2]
(into [1] 2)
; IllegalArgumentException Don't know how to create ISeq from: java.lang.Long  clojure.lang.RT.seqFrom (RT.java:542)

(defn looper1
  [the-stuff & the-stuffs]
  (str the-stuff, "then", the-stuffs))

(looper1 [1 2 3] 4 5)
; "[1 2 3]then(4 5)"
(looper1 1 2 3)
; "1then(2 3)"
