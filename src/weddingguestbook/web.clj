(ns weddingguestbook.web
  (:require [compojure.core :refer [defroutes]]
            [ring.adapter.jetty :as ring]
            [compojure.route :as route]
            [ring.middleware.defaults :refer [wrap-defaults site-defaults]]
            [weddingguestbook.controllers.messages :as messages]
            [weddingguestbook.views.layout :as layout]
            [weddingguestbook.models.migration :as schema])
  (:gen-class))

(defroutes routes
           messages/routes
           (route/resources "/")
           (route/not-found (layout/four-oh-four)))

(def application (wrap-defaults routes site-defaults))

(defn start [port]
  (ring/run-jetty application {:port port
                               :join? false}))

(defn -main []
  (schema/migrate)
  (let [port (Integer. (or (System/getenv "PORT") "8080"))]
    (start port)))