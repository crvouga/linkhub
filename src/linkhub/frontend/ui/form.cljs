(ns linkhub.frontend.ui.form)

(defn view [props & children]
  [:form 
   (merge props {:on-submit (-> props :form/on-submit)}) children])
  