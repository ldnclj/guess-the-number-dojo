(ns guess-the-number-dojo.core (:gen-class))


(defn ask-for-feedback []
  (loop
   []
    (println "Tell me am I right (y)es, or is your number l(ower), h(higher) than my guess? ")
    (let [line  (read-line)]
      (cond (re-matches #"y|h|l" line) line
            :default (recur)))))

;; guesses so far to be added
(defn guess-to-limit [lowbound highbound counter]
  (let
    [guess (quot (+ lowbound highbound) 2)
     _ (println "My guess is " guess)
     feedback (ask-for-feedback)]
     (if (= feedback "y")
       counter
       (let
           [[new-higher new-lower] (if (= feedback "h") [highbound (inc guess)] [guess lowbound])]
         (recur new-lower new-higher (inc counter))))))

(defn player-as-chooser "" [limit]
  (println "Choose a whole number from 1 to" limit "and I will guess")

  (println "Hit enter when ready")
  (read-line)
  (let [counter (guess-to-limit 1 limit 1)]
    (println "got it in" counter "guesses")))

(defn -main
  [& args]
  (player-as-chooser 10))

