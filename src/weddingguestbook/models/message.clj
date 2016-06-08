(ns weddingguestbook.models.message

  (:require [clojure.java.jdbc :as sql]))

(def spec (or (System/getenv "DATABASE_URL")
              "postgresql://localhost:5432/shouter"))

(defn all []
  (into [] (sql/query spec ["select * from messages order by id desc"])))

(defn create [message]
  (sql/insert! spec :messages [:body] [message]))

