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
    (cond (= feedback "y") counter
          (= highbound lowbound) (do  (println "Cheater! I'm not playing anymore!") -1)
          :default (let
                    [[new-higher new-lower] (if (= feedback "h") [highbound (inc guess)] [guess lowbound])]
                     (recur new-lower new-higher (inc counter))))))

(defn player-as-chooser "" [limit]
  (println "Choose a whole number from 1 to" limit "and I will guess")

  (println "Hit enter when ready")
  (read-line)
  (let [counter (guess-to-limit 1 limit 1)]
    (if (> counter 0)
      (println "got it in" counter "guesses")
      (println "I'm going home"))))

(defn play-as-guesser "Guess the number." [n]
   (let [bulls-eye (inc (rand-nth (range 100)))]
     (loop [current-input (Integer. n)]

       (if (= current-input bulls-eye)
           (println "Bulls eye!")
        (do
         (if (> current-input bulls-eye)
             (println "Number entered is too big. Go lower:")
             (println "Number entered is too low. Go higher:"))
         (recur (Integer. (read-line))))))))

(defn -main
  [& args]
  (loop
   []
    (println "Would you like to (g)uess or (c)hoose or (q)uit?")
    (let [line  (read-line)]
      (cond
        (re-matches #"g|c" line)
        (if (= line "c") (player-as-chooser 100)
            (play-as-guesser (read-line) ))
        :default (recur)))))

;(println "Enter a number between 1 and 100:")
