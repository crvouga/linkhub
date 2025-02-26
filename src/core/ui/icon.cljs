(ns core.ui.icon)


(defn spinner [props]
  [:svg (merge {:xmlns "http://www.w3.org/2000/svg", :fill "currentColor", :stroke "currentColor", :viewBox "0 0 24 24"} props)
   [:path {:fillRule "evenodd", :d "M12 19a7 7 0 100-14 7 7 0 000 14zm0 3c5.523 0 10-4.477 10-10S17.523 2 12 2 2 6.477 2 12s4.477 10 10 10z", :clipRule "evenodd", :opacity "0.2"}]
   [:path {:d "M2 12C2 6.477 6.477 2 12 2v3a7 7 0 00-7 7H2z"}]])

(defn home [props]
  [:svg (merge {:xmlns "http://www.w3.org/2000/svg", :viewBox "0 0 24 24", :fill "currentColor"} props)
   [:path {:d "M11.47 3.841a.75.75 0 0 1 1.06 0l8.69 8.69a.75.75 0 1 0 1.06-1.061l-8.689-8.69a2.25 2.25 0 0 0-3.182 0l-8.69 8.69a.75.75 0 1 0 1.061 1.06l8.69-8.689Z"}]
   [:path {:d "m12 5.432 8.159 8.159c.03.03.06.058.091.086v6.198c0 1.035-.84 1.875-1.875 1.875H15a.75.75 0 0 1-.75-.75v-4.5a.75.75 0 0 0-.75-.75h-3a.75.75 0 0 0-.75.75V21a.75.75 0 0 1-.75.75H5.625a1.875 1.875 0 0 1-1.875-1.875v-6.198a2.29 2.29 0 0 0 .091-.086L12 5.432Z"}]])

(defn user-circle [props]
  [:svg (merge {:xmlns "http://www.w3.org/2000/svg", :viewBox "0 0 24 24", :fill "currentColor"} props)
   [:path {:fillRule "evenodd", :d "M18.685 19.097A9.723 9.723 0 0 0 21.75 12c0-5.385-4.365-9.75-9.75-9.75S2.25 6.615 2.25 12a9.723 9.723 0 0 0 3.065 7.097A9.716 9.716 0 0 0 12 21.75a9.716 9.716 0 0 0 6.685-2.653Zm-12.54-1.285A7.486 7.486 0 0 1 12 15a7.486 7.486 0 0 1 5.855 2.812A8.224 8.224 0 0 1 12 20.25a8.224 8.224 0 0 1-5.855-2.438ZM15.75 9a3.75 3.75 0 1 1-7.5 0 3.75 3.75 0 0 1 7.5 0Z", :clipRule "evenodd"}]])

(defn arrow-left [props]
  [:svg (merge {:xmlns "http://www.w3.org/2000/svg", :viewBox "0 0 24 24", :fill "currentColor"} props)
   [:path {:fill-rule "evenodd", :d "M11.03 3.97a.75.75 0 0 1 0 1.06l-6.22 6.22H21a.75.75 0 0 1 0 1.5H4.81l6.22 6.22a.75.75 0 1 1-1.06 1.06l-7.5-7.5a.75.75 0 0 1 0-1.06l7.5-7.5a.75.75 0 0 1 1.06 0Z", :clip-rule "evenodd"}]])


(defn door-open [props]
  [:svg (merge {:xmlns "http://www.w3.org/2000/svg", :viewBox "0 0 576 512" :fill "currentColor"} props)
   [:path {:d "M320 32c0-9.9-4.5-19.2-12.3-25.2S289.8-1.4 280.2 1l-179.9 45C79 51.3 64 70.5 64 92.5L64 448l-32 0c-17.7 0-32 14.3-32 32s14.3 32 32 32l64 0 192 0 32 0 0-32 0-448zM256 256c0 17.7-10.7 32-24 32s-24-14.3-24-32s10.7-32 24-32s24 14.3 24 32zm96-128l96 0 0 352c0 17.7 14.3 32 32 32l64 0c17.7 0 32-14.3 32-32s-14.3-32-32-32l-32 0 0-320c0-35.3-28.7-64-64-64l-96 0 0 64z"}]])

(defn x-mark [props]
  [:svg (merge {:xmlns "http://www.w3.org/2000/svg", :fill "none", :viewBox "0 0 24 24", :strokeWidth "1.5", :stroke "currentColor", :className "size-6"} props)
   [:path {:strokeLinecap "round", :strokeLinejoin "round", :d "M6 18 18 6M6 6l12 12"}]])