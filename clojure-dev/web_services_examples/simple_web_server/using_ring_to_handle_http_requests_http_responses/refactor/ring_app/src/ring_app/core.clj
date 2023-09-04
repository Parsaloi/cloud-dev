(ns ring_app.core
  (require [ring.adapter.jetty :as jetty]
           [ring.middleware.reload :refer [wrap-reload]]))

(defn handler [request-map]
  {:status 200
   :headers {"Content-Type" "text/html"}
   :body (str "<html><body> Yello, my IP is: "
              (:remote-addr request-map)
              "</body></html>")})

(defn wrap-nocache [handler]
  (fn [request]
    (-> request
        handler
        (assoc-in [:headers "Pragma"] "no-cache"))))

(defn -main []
  (jetty/run-jetty
    (-> handler
        var
        wrap-nocache
        wrap-reload)
    {:port 3001
     :join? false}))
