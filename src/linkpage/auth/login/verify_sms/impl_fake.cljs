(ns linkpage.auth.login.verify-sms.impl-fake
  (:require [clojure.core.async :refer [<! go timeout]]
            [linkpage.auth.login.verify-sms.interface :refer [send-code! verify-code!]]))

(def fake-code "123")

(defmethod send-code! :verify-sms-impl/fake [i]
  (go
    (<! (timeout 1500))
    (let [phone-number (-> i :user/phone-number)]
      (println "Sending code " fake-code " to " phone-number)
      [:result/ok i])))

(defmethod verify-code! :verify-sms-impl/fake [i]
  (go
    (<! (timeout 1500))
    (let [phone-number (-> i :user/phone-number)
          code (-> i :verify-sms/code)]
      (println "Verifying code " code " for " phone-number)
      (if (= code fake-code)
        [:result/ok i]
        [:result/error {:error/data [:verify-sms-error/wrong-code]}]))))

