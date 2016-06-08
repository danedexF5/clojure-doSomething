(ns weddingguestbook.views.messages
  (:require [weddingguestbook.views.layout :as layout]
            [hiccup.core :refer [h]]
            [hiccup.form :as form]
            [ring.util.anti-forgery :as anti-forgery]))

(defn message-form []
  [:div {:id "message-form" :class "sixteen columns alpha omega"}
   (form/form-to [:post "/"]
                 (anti-forgery/anti-forgery-field)
                 ;(form/label "name" "Leave your name here!")
                 ;(form/text-area "name")
                 (form/label "message" "Leave your message here!")
                 (form/text-area "message")
                 (form/submit-button "Submit"))])

(defn display-messages [messages]
  [:div {:class "messages sixteen columns alpha omega"}
   (map
     (fn [message] [:h2 {:class "message"} (h (:body message))])
     messages)])

(defn index [messages]
  (layout/common "Wedding Guest Book"
                 (message-form)
                 [:div {:class "clear"}]
                 (display-messages messages)))
