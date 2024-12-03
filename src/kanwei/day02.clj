(ns kanwei.day02)

(def lines (line-seq (clojure.java.io/reader (clojure.java.io/resource "day02-input.txt"))))

(defn check-line [line]
  (reduce (fn [m number]
            {:biggest       ((fnil max 0) (:biggest m) number)
             :smallest      ((fnil min 0) (:smallest m) number)
             :increasing?   (and (:increasing? m) (>= number (:biggest m)))
             :decreasing?   (and (:decreasing? m) (<= number (:smallest m)))
             :previous      number
             :within-range? (and (:within-range? m)
                                 (or (nil? (:previous m))
                                     (< 0 (abs (- (:previous m) number)) 4)))
             })
          {:biggest       0
           :smallest      999999999999999999999
           :increasing?   true
           :decreasing?   true
           :previous      nil
           :within-range? true}
          line))

(reduce (fn [safe line]
          (let [result (check-line (map parse-long (clojure.string/split line #"\s+")))]

            (if (and (true? (:within-range? result))
                     (or (:increasing? result)
                         (:decreasing? result)))
             (inc safe)
              safe)))

        0
        lines)

; 686
