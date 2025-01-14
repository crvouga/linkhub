(ns linkpage.auth.login.frontend
  (:require
   [linkpage.frontend.routing :as routing]
   [linkpage.frontend.store :as store]
   [linkpage.core.result :as result]
   [linkpage.frontend.ui.button :as button]
   [linkpage.frontend.toast :as toast]
   [linkpage.frontend.ui.text-field :as text-field]))

(defmulti transition store/msg-type)

(defmethod transition :default [i] i)

(defmethod transition :store/initialized [i]
  (-> i
      (assoc-in [:store/state ::send-code] [:result/not-asked])
      (assoc-in [:store/state ::verify-code] [:result/not-asked])))

(defn- send-code-eff [i]
  [:rpc/send! {:rpc/req [:login-rpc/send-code {:user/phone-number (-> i :store/state ::phone-number)}]
               :rpc/res #(vector ::sent-code %)}])

(defmethod transition ::submitted-send-code-form [i]
  (-> i
      (update-in [:store/state] assoc ::send-code [:result/loading])
      (update-in [:store/effs] conj (send-code-eff i))))

(defmethod transition ::sent-code [i]
  (let [sent-code (store/msg-payload i)
        msgs (when (result/ok? sent-code)
               [[:routing/push [:route/login-verify-code (result/payload sent-code)]]
                [:toaster/show (toast/info "Code sent")]])]
    (-> i
        (update-in [:store/state] assoc ::send-code sent-code)
        (update-in [:store/msgs] concat msgs))))

(defmethod transition ::inputted-phone-number [i]
  (-> i
      (assoc-in [:store/state ::phone-number] (store/msg-payload i))))

(defn sending-code? [i]
  (-> i :store/state ::send-code first (= :result/loading)))

(defn view-layout [title body]
  [:main.container {:style {:padding-top "4rem"}}
   [:header [:h1 title]]
   [:section body]])

(defmethod routing/view :route/login [i]
  [view-layout "Login"
   [:form
    {:on-submit #(do (.preventDefault %) (store/put! i [::submitted-send-code-form]))}
    [text-field/view
     {:text-field/label "Phone Number"
      :text-field/value (-> i :store/state ::phone-number)
      :text-field/required? true
      :text-field/disabled? (sending-code? i)
      :text-field/on-change #(store/put! i [::inputted-phone-number %])}]
    [button/view
     {:button/type :button-type/submit
      :button/loading? (sending-code? i)
      :button/label "Send Code"}]]])

(defmethod transition ::inputted-code [i]
  (-> i
      (assoc-in [:store/state ::code] (store/msg-payload i))))

(defn verify-code-eff [i]
  [:rpc/send! {:rpc/req [:login-rpc/verify-code {:user/phone-number (-> i routing/route-payload :user/phone-number)
                                                 :verify-sms/code (-> i :store/state ::code)}]
               :rpc/res #(vector ::verified-code %)}])

(defmethod transition ::submitted-verify-code-form [i]
  (-> i
      (update-in [:store/state] assoc ::verify-code [:result/loading])
      (update-in [:store/effs] conj (verify-code-eff i))))

(defmethod transition ::verified-code [i]
  (let [payload (store/msg-payload i)
        msgs (when (result/ok? payload)
               [[:login/authenticated]])]
    (-> i
        (update-in [:store/state] assoc ::verify-code payload)
        (update-in [:store/msgs] concat msgs))))

(defn verifying-code? [i]
  (-> i :store/state ::verify-code first (= :result/loading)))

(defmethod routing/view :route/login-verify-code [i]
  [view-layout "Verify Code"
   [:form
    {:on-submit #(do (.preventDefault %) (store/put! i [::submitted-verify-code-form]))}
    [:p "Enter the code we sent to " [:strong (-> i routing/route-payload :user/phone-number)]]
    [text-field/view
     {:text-field/label "Code"
      :text-field/value (-> i :store/state ::code)
      :text-field/required? true
      :text-field/disabled? (verifying-code? i)
      :text-field/on-change #(store/put! i [::inputted-code %])}]
    [button/view
     {:button/type :button-type/submit
      :button/loading? (verifying-code? i)
      :button/label "Verify Code"}]]])

(store/register-transition! transition)

