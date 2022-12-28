(ns fwpd.core)
(def filename "suspects.csv")

; (slurp filename)
; => "Edward Cullen,10\nBella Swan,0\nCharlie Swan,0\nJacob Black,3\nCarlisle Cullen,6\n"

(def vamp-keys [:name :glitter-index])

(defn str->int
  [str]
  (Integer. str))
; (str->int "3")
; => 3

(def conversions {:name identity
                  :glitter-index str->int})
(identity "x")
; => "x"

(defn convert
  [vamp-key value]
  ((get conversions vamp-key) value))

; single quote -> literal list; double quote -> string
(get {:a 'b :c 'd :e 'f} :c)
; => d'
(get {:a "b" :c "d" :e "f"} :c)
; => "d"

(get {:a "b" :c identity :e "f"} :c)
; => #object[clojure.core$identity 0x5dad592f "clojure.core$identity@5dad592f"]
(def my-conversions {:c identity})
(get my-conversions :c)
; => #object[clojure.core$identity 0x5dad592f "clojure.core$identity@5dad592f"]

(convert :glitter-index "3")
; => 3

(defn parse
  "Convert a CSV into rows of columns"
  [string]
  (map #(clojure.string/split % #",")
       (clojure.string/split string #"\n")))

(parse (slurp filename))
; => (["Edward Cullen" "10"] ["Bella Swan" "0"] ["Charlie Swan" "0"] ["Jacob Black" "3"] ["Carlisle Cullen" "6"])
(clojure.string/split (slurp filename) #"\n")
; => ["Edward Cullen,10" "Bella Swan,0" "Charlie Swan,0" "Jacob Black,3" "Carlisle Cullen,6"]
(def my-regex (re-pattern "\n"))
(clojure.string/split (slurp filename) my-regex)
; => ["Edward Cullen,10" "Bella Swan,0" "Charlie Swan,0" "Jacob Black,3" "Carlisle Cullen,6"]

(defn mapify
  "Return a seq of maps like {:name \"Edward Cullen\" :glitter-index 10}"
  [rows]
  (map (fn [unmapped-row]
         (reduce (fn [row-map [vamp-key value]]
                   (assoc row-map vamp-key (convert vamp-key value)))
                 {}
                 (map vector vamp-keys unmapped-row)))
       rows))

(vector vamp-keys ["Edward Cullen" "10"])
; => [[:name :glitter-index] ["Edward Cullen" "10"]]
(map vector vamp-keys ["Edward Cullen" "10"])
; => ([:name "Edward Cullen"] [:glitter-index "10"])
(assoc {} :name "Edward Cullen")
; => {:name "Edward Cullen"}
; (assoc {} [:name "Edward Cullen"])
; => Arity Exception
(assoc {:name "Edward Cullen"} :glitter-index 3)
; => {:name "Edward Cullen", :glitter-index 3}

(first (mapify (parse (slurp filename))))
; => {:name "Edward Cullen", :glitter-index 10}
(mapify (parse (slurp filename)))
; => ({:name "Edward Cullen", :glitter-index 10} {:name "Bella Swan", :glitter-index 0} {:name "Charlie Swan", :glitter-index 0} {:name "Jacob Black", :glitter-index 3} {:name "Carlisle Cullen", :glitter-index 6})

(defn glitter-filter
  [minimum-glitter records]
  (filter #(>= (:glitter-index %) minimum-glitter) records))
(glitter-filter 3 (mapify (parse (slurp filename))))
; => ({:name "Edward Cullen", :glitter-index 10} {:name "Jacob Black", :glitter-index 3} {:name "Carlisle Cullen", :glitter-index 6})

(defn glitter-filter-ex1
  [minimum-glitter records]
  ; (map #(get % :name) (filter #(>= (:glitter-index %) minimum-glitter) records))
  (map :name (filter #(>= (:glitter-index %) minimum-glitter) records))
  )
(glitter-filter-ex1 3 (mapify (parse (slurp filename))))
; => ("Edward Cullen" "Jacob Black" "Carlisle Cullen")

(get {:name "Edward", :glitter-index 10} :name)
(map #(get % :name) [{:name "Edward", :glitter-index 10}])
; => ("Edward")

(mapify (parse (slurp filename)))

(defn append
  [name glitter-index]
  (conj (mapify (parse (slurp filename))) {:name name :glitter-index glitter-index})
  )
(append "Bob" 5)
; => ({:name "Bob", :glitter-index 5} {:name "Edward Cullen", :glitter-index 10} {:name "Bella Swan", :glitter-index 0} {:name "Charlie Swan", :glitter-index 0} {:name "Jacob Black", :glitter-index 3} {:name "Carlisle Cullen", :glitter-index 6})

(defn append4
  [name glitter-index]
  (conj (vector (mapify (parse (slurp filename)))) {:name name :glitter-index glitter-index})
  )
(append4 "Bob" 5)
; => [({:name "Edward Cullen", :glitter-index 10} {:name "Bella Swan", :glitter-index 0} {:name "Charlie Swan", :glitter-index 0} {:name "Jacob Black", :glitter-index 3} {:name "Carlisle Cullen", :glitter-index 6}) {:name "Bob", :glitter-index 5}]

(defn append5
  [name glitter-index]
  (into (vector (mapify (parse (slurp filename)))) {:name name :glitter-index glitter-index})
  )
(append5 "Bob" 5)
; => [({:name "Edward Cullen", :glitter-index 10} {:name "Bella Swan", :glitter-index 0} {:name "Charlie Swan", :glitter-index 0} {:name "Jacob Black", :glitter-index 3} {:name "Carlisle Cullen", :glitter-index 6}) [:name "Bob"] [:glitter-index 5]]

(defn append2
  [v-name glitter-index]
  (concat (mapify (parse (slurp filename))) {:name v-name :glitter-index glitter-index}
  ))
(append2 "Bob" 5)
; => ({:name "Edward Cullen", :glitter-index 10} {:name "Bella Swan", :glitter-index 0} {:name "Charlie Swan", :glitter-index 0} {:name "Jacob Black", :glitter-index 3} {:name "Carlisle Cullen", :glitter-index 6} [:name "Bob"] [:glitter-index 5])
(defn append6
  [v-name glitter-index]
  (concat (mapify (parse (slurp filename))) [{:name v-name :glitter-index glitter-index}]
  ))
(append6 "Bob" 5)
; => ({:name "Edward Cullen", :glitter-index 10} {:name "Bella Swan", :glitter-index 0} {:name "Charlie Swan", :glitter-index 0} {:name "Jacob Black", :glitter-index 3} {:name "Carlisle Cullen", :glitter-index 6} {:name "Bob", :glitter-index 5})

(defn append3
  [v-name glitter-index]
  (into (mapify (parse (slurp filename))) {:name v-name :glitter-index glitter-index}
  ))
(append3 "Bob" 5)
; => ([:glitter-index 5] [:name "Bob"] {:name "Edward Cullen", :glitter-index 10} {:name "Bella Swan", :glitter-index 0} {:name "Charlie Swan", :glitter-index 0} {:name "Jacob Black", :glitter-index 3} {:name "Carlisle Cullen", :glitter-index 6})

(concat `(1 2) [3])
; => (1 2 3)


(parse (slurp filename))
; => (["Edward Cullen" "10"] ["Bella Swan" "0"] ["Charlie Swan" "0"] ["Jacob Black" "3"] ["Carlisle Cullen" "6"])

(def unmapped-row ["Edward Cullen" "10"])
(vector vamp-keys unmapped-row)
; => [[:name :glitter-index] ["Edward Cullen" "10"]]

(def unmapped-row ["Edward Cullen" "10"])
(reduce (fn [row-map [vamp-key value]]
         (assoc row-map vamp-key (convert vamp-key value)))
       {}
       (map vector vamp-keys unmapped-row))
; => {:name "Edward Cullen", :glitter-index 10}

(def validationz {:name contains?
                  :glitter-index contains?})


(defn single-validate
  [vamp-key value]
  ((get validationz vamp-key) {vamp-key value} vamp-key))

(single-validate :name "bob")
; => true
(single-validate :name "bob")
; null pointer if false

(seq {:name "bob" :glitter 5})
; => ([:name "bob"] [:glitter 5])

(defn append10
  [record]
  (conj (mapify (parse (slurp filename))) record)
  )
(append10 {:name "Bob" :glitter-index 5})
; => ({:name "Bob", :glitter-index 5} {:name "Edward Cullen", :glitter-index 10} {:name "Bella Swan", :glitter-index 0} {:name "Charlie Swan", :glitter-index 0} {:name "Jacob Black", :glitter-index 3} {:name "Carlisle Cullen", :glitter-index 6})

(defn validate
  [validations record]
  )
(defn append-and-validate
  [record]
  (validate validationz record)
  (conj (mapify (parse (slurp filenmae))) record)
  )


; Exercises
; Exercise 1. Turn the result of your glitter filter into a list of names.
; Exercise 2. Write a function, append, which will append a new suspect to your list of suspects.
; Exercise 3. Write a function, validate, which will check that :name and :glitter-index are present when you append. The validate function should accept two arguments: a map of keywords to validating functions, similar to conversions, and the record to be validated.
; Exercise 4. Write a function that will take your list of maps and convert it back to a CSV string. You’ll need to use the clojure.string/join function.

