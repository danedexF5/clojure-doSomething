(ns weddingguestbook.controllers.messages

  (:require [compojure.core :refer [defroutes GET POST]]
            [clojure.string :as str]
            [ring.util.response :as ring]
            [weddingguestbook.views.messages :as view]
            [weddingguestbook.models.message :as model]))

(defn index []
  (view/index (model/all)))

(defn create
  [message]
  (when-not (str/blank? message)
    (model/create message))
  (ring/redirect "/"))

(defroutes routes
           (GET  "/" [] (index))
           (POST "/" [message] (create message)))