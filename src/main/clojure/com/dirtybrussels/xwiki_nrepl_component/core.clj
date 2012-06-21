(ns com.dirtybrussels.xwiki-nrepl-component.core
  (:use [clojure.tools.nrepl.server :only [start-server stop-server]])
  (:gen-class
    :methods [[hello [String] String]
              [startRepl [] String]
              [inject [String Object] Object]
              [merge  [java.util.HashMap] void]]
    :name com.dirtybrussels.xwiki_nrepl_component.XWikiNReplComponent))

(def server (atom nil))

(def internals (atom { }))

(defn stop-repl []
  (stop-server @server))

(defn start-repl []
  (if @server
    (do
      (println "nRepl stopped listening on port 7888")
      (.close (:ss @@server))))
  (println "nRepl started on port 7888")
  (reset! server (start-server :port 7888)))

(defn -hello [_ s]
  (str "Hello from clojure! " s))

(defn -startRepl [_]
  (start-repl)
  "nRepl started on port 7888")

(defn -inject [_ keyname component]
  (reset! internals (assoc @internals (keyword keyname) component)))

(defn -merge [_ hmap]
  (reset! internals (into @internals hmap)))

(defn print-methods
  ([o q]
   (doseq [x (.getMethods (class o))
           :when (re-seq (re-pattern (str "^" q)) (.getName x))]
     (println x)))
  ([o]
   (doseq [x (.getMethods (class o))]
     (println x))))
