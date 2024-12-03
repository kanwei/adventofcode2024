(ns kanwei.day03)

(def lines (line-seq (clojure.java.io/reader (clojure.java.io/resource "day03-input.txt"))))

(def mul-regex #"mul\(([0-9]+),([0-9]+)\)")

(re-seq mul-regex "xmul(2,4)%&mul[3,7]!@^do_not_mul(5,5)+mul(32,64]then(mul(11,8)mul(8,5))")

(defn calc-mult [[_ a b]]
  (* (parse-long a) (parse-long b)))

(defn calc-line [line]
  (let [matches (re-seq mul-regex line)]
    (reduce + (map calc-mult matches))))

(reduce + (map calc-line lines))

; 161085926