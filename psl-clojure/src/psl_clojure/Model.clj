(ns psl-clojure.Model
  (:gen-class
   :extends org.linqs.psl.model.Model
   :state state
   :methods [[argNames [String] clojure.lang.PersistentVector]
             [addPredicate [java.util.HashMap]
              org.linqs.psl.model.predicate.StandardPredicate]
             ]
   :exposes-methods {}
   :init init
   :constructors {[org.linqs.psl.database.DataStore] []})
  (:require [clj-util.core :as u])
  (:import
   [org.linqs.psl.model.predicate StandardPredicate]))

(defn -init
  [datastore]
  [[] (atom {:preds {} :datastore datastore})])

(defn -addPredicate
  "Add a predicate to the model."
  [this args-map]
  (let [pred-name (get args-map "predicate")
        arg-names (get args-map "names")
        arg-types (into-array org.linqs.psl.model.term.ConstantType
                              (get args-map "types"))
        datastore (get (deref (.state this)) :datastore)
        predicate (StandardPredicate/get pred-name arg-types)]
    (swap! (.state this) assoc-in [:preds pred-name] {:arg-names arg-names})
    
    (.registerPredicate datastore predicate)
    predicate))

(defn -argNames
  "List names of arguments of predicate with given name."
  [this pred-name]
  (let [arg-names (get-in @(.state this) [:preds pred-name :arg-names])]
    arg-names))
