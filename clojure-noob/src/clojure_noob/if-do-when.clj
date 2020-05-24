(ns clojure-noob.core
  (:gen-class))

(if true
  "By Odin's Elbow!")
; => "By Odin's Elbow"

(if false
  "By Odin's Elbow!")
; => nil
(if false
  "By Odin's Elbow!"
  "By Thor's Beard!")
; => "By Thor's Beard!"

(if true
  (do (println "First do!")
      "By Odin's Elbow!")
  )
; => "First do!"
; => "By Odin's Elbow!"

(if false
  (do (println "First do!")
      "By Odin's Elbow!")
  )
; => nil


(when true
  (println "First do!")
  "By Odin's Elbow!"
  )
; => "First do!"
; => "By Odin's Elbow!"

(when false
  (println "First do!")
  "By Odin's Elbow!"
  )
; => nil
