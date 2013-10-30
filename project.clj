(defproject clojure-ttt "0.1.0-SNAPSHOT"
  :description "Exploring the Clojure language by writing a simple, yet unbeatable, tic tac toe game"
  :url "github.com/pszals"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
; :main clojure-ttt.board
  :dependencies [[org.clojure/clojure "1.5.0"]]
  :profiles {:dev {:dependencies [[speclj "2.5.0"]]}}
  :plugins [[speclj "2.5.0"]]
  :test-paths ["spec/"])
