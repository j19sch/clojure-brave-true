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