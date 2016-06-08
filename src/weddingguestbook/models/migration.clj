(ns weddingguestbook.models.migration
  (:require [clojure.java.jdbc :as sql]
            [weddingguestbook.models.message :as message]))

(defn migrated? []
  (-> (sql/query message/spec
                 [(str "select count(*) from information_schema.tables "
                       "where table_name='messages'")])
      first :count pos?))

(defn migrate []
  (when (not (migrated?))
    (print "Creating database structure...") (flush)
    (sql/db-do-commands message/spec
                        (sql/create-table-ddl
                          :messages
                          [[:id :serial "PRIMARY KEY"]
                           [:body :varchar "NOT NULL"]
                           [:created_at :timestamp
                            "NOT NULL" "DEFAULT CURRENT_TIMESTAMP"]]))
    (println " done")))
