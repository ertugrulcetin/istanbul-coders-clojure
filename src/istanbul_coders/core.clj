(ns istanbul-coders.core
  (:require [clojure.set :as set]
            [clojure.java.io :as io]
            [kezban.core :refer :all])
  (:import (java.util HashMap)))


(+ 1 2)


(+ 1 2 3 (* 2 3))


(println "Istanbul Coders")

;;define my-add fn
(defn my-add
  [x y z]
  (+ x y z)
  (println "a"))

(my-add 1 1 1)


;;overload fn
(defn argcount
  ([] 0)
  ([x] 1)
  ([x y] 2)
  ([x y & more]
   (+ (argcount x y) (count more))))
(argcount :a :a)


(type (for [i (range 1 10000000)]
        (inc i)))

(def x [1 2 3 4 5 6 7 8 9 10])
(map inc x)
(filter even? x)
(reduce + x)


;;Data Structures
(def my-list '(1 2 3 4))
my-list
(cons 0 my-list)
(cons -1 my-list)
(cons -1 (cons 0 my-list))
(conj my-list -2)

(reduce (fn [x y]
          (cons y x)) '(-1 -2 -3 -4) '(1 2 3 4 5))


(def my-vec [:a :b :c])
(conj my-vec :d)
(conj my-vec :e)
(cons :f my-vec)


(def my-map {:a 1 :b 2 :c 3})
(assoc my-map :d 4)
(assoc my-map :e 5)
(dissoc my-map :a)


(= (assoc my-map :e 5) (assoc my-map :e 5))
(identical? (assoc my-map :e 5) (assoc my-map :e 5))


(def my-set #{\a \b \c})
(set/union my-set #{\b \c \d})
(set/difference my-set #{\b \c})


;;Java interop
(.toUpperCase "fred")
(.-x (java.awt.Point. 1 2))
(System/currentTimeMillis)

;;(.get (System/getProperties) "os.name")
(.. System (getProperties) (get "os.name"))
(.get (System/getProperties) "os.name")

(doto (HashMap.)
  (.put "a" 1)
  (.put "b" 2))


;;Macro
(def c 5)
(->> c (+ 3) (/ 2) (- 1))
(- 1 (/ 2 (+ 3 c)))

(use 'clojure.walk)
(macroexpand-all '(->> c (+ 3) (/ 2) (- 1)))


(if-let [a 1]
  (if-let [b (+ a 2)]
    (if-let [c (* b 2)]
      :t
      :f)))


;;refactor with if-let* macro
(if-let* [a 1
          b (+ a 2)
          c (* b 2)]
         :t
         :f)


(let [a 1
      b (+ a 1)
      c nil
      d (println "Selam")]
  {:a a
   :b b
   :c c
   :d d})


(letm [a 1
       b (+ a 1)
       c nil
       d (println "Selam")])


(defn return-nil
  [x]
  nil)

;(return-nil (inc 1))
(some-> 1 inc  (println "aa"))

;;Ruby like string interpolation
(defmacro fmt
  [^String string]
  (let [-re   #"#\{(.*?)\}"
        fstr  (clojure.string/replace string -re "%s")
        fargs (map #(read-string (second %)) (re-seq -re string))]
    `(format ~fstr ~@fargs)))

(def name "Mister")
(def surname "Metaphor")
(def seq [1 2 3])

(println (fmt "My name is: #{name}"))
(println (fmt "Hello #{name} #{(clojure.string/join \" \" surname)}!"))
(println (fmt "First element of #{seq} is #{(first seq)}!"))


;;Example code
(def scenes [{:subject "Frankie"
              :action  "say"
              :object  "relax"}

             {:subject "Lucy"
              :action  "❤s"
              :object  "Clojure"}

             {:subject "Rich"
              :action  "tries"
              :object  "a new conditioner"}])

;; Define a function
(defn people-in-scenes
  [scenes]
  (->> scenes
       (map :subject)
       (interpose ", ")
       (reduce str)))

;; Who's in our scenes?
(println "People:" (people-in-scenes scenes))


;;Java Swing
(use 'seesaw.core)
(native!)

(def f (frame :title "Get to know Seesaw"))
(-> f pack! show!)

(config f :title)

(config! f :title "No RLY, get to know Seesaw!")
(config! f :content "This is some content")

(def lbl (label "I'm another label"))
(config! f :content lbl)

(defn display [content]
  (config! f :content content)
  content)

(display lbl)
(config! lbl :background :pink :foreground "#00f")
(config! lbl :font "ARIAL-BOLD-21")

(use 'seesaw.font)
(config! lbl :font (font :name :monospaced
                         :style #{:bold :italic}
                         :size 18))

(alert "I'm an alert")

(input "What's your favorite color?")
