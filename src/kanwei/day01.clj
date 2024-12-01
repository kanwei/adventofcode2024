(ns kanwei.day01)

(def lines (line-seq (clojure.java.io/reader (clojure.java.io/resource "day01-input.txt"))))

(def cols (reduce (fn [coll [a b]]
           (-> coll
               (update :col-a conj (parse-long a))
               (update :col-b conj (parse-long b))
               ))
         {:col-a [] :col-b []}
         (map #(clojure.string/split % #"\s+") lines)))

(def col-a-sorted (sort (:col-a cols)))
(def col-b-sorted (sort (:col-b cols)))

(def m-v (apply assoc {} (interleave col-a-sorted col-b-sorted)))

(reduce (fn [diffs [a b]]
          (+ diffs (abs (- a b))))
        0
        m-v)

; 1222801

; Part 2

(def col-b-counts
  (reduce (fn [counts n]
            (update counts n (fnil inc 0)))
          {}
          col-b-sorted))

(reduce (fn [diffs n]
          (+ diffs (* n (or (get col-b-counts n)
                            0))))
        0
        col-a-sorted)

; 22545250