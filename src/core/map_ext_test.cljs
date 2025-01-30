(ns core.map-ext-test
  (:require [cljs.test :refer [deftest testing is]]
            [core.map-ext :as map-ext]
            [clojure.string :as str]))

(deftest convert-keys-recursively-test
  (testing "handles nil input"
    (is (nil? (map-ext/convert-keys-recursively nil identity))))

  (testing "handles non-collection input"
    (is (= 42 (map-ext/convert-keys-recursively 42 identity)))
    (is (= "test" (map-ext/convert-keys-recursively "test" identity))))

  (testing "converts keys in maps"
    (is (= {:a-key 1 :b-key 2}
           (map-ext/convert-keys-recursively
            {"a_key" 1 "b_key" 2}
            #(keyword (str/replace % "_" "-"))))))

  (testing "converts nested maps recursively"
    (is (= {:outer {:inner 42}}
           (map-ext/convert-keys-recursively
            {"outer" {"inner" 42}}
            keyword))))

  (testing "converts maps inside sequences"
    (is (= [{:a 1} {:b 2}]
           (map-ext/convert-keys-recursively
            [{"a" 1} {"b" 2}]
            keyword)))))
