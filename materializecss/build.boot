(set-env!
  :resource-paths #{"resources"}
  :dependencies '[[adzerk/bootlaces   "0.1.9" :scope "test"]
                  [cljsjs/boot-cljsjs "0.4.6" :scope "test"]
		  [cljsjs/jquery-ui "1.11.4-0"]])

(require '[adzerk.bootlaces :refer :all]
         '[cljsjs.boot-cljsjs.packaging :refer :all])

(def +version+ "0.95.3-0")

(task-options!
  pom  {:project     'cljsjs/materializecss
        :version     +version+
        :description "Materialize Css Framework"
        :url         "http://materializecss.com/"
        :license     {"MIT" "http://opensource.org/licenses/MIT"}
        :scm         {:url "https://github.com/cljsjs/packages"}})

(deftask package []
  (comp
    (download :url "https://cdnjs.cloudflare.com/ajax/libs/materialize/0.95.3/js/materialize.js"
              :checksum "262A2642B1C06CAF3753E129314D3D80")
    (download :url "https://cdnjs.cloudflare.com/ajax/libs/materialize/0.95.3/js/materialize.min.js"
              :checksum "66F1CECD4DAF01EE9A4EA9A79051A8C2")
    (download :url "https://cdnjs.cloudflare.com/ajax/libs/materialize/0.95.3/css/materialize.css"
              :checksum "635D36BB67F7112F2D688956C911F1AD")
    (download :url "https://cdnjs.cloudflare.com/ajax/libs/materialize/0.95.3/css/materialize.min.css"
              :checksum "862C10528B22A0C7F72B0A21B6BF0AE1")

(sift :move {#"materialize.js" "cljsjs/development/materialize.inc.js"
             #"materialize.min.js" "cljsjs/production/mazerialize.min.inc.js"
	     #"materialize.css" "cljsjs/development/materialize.inc.css"
	     #"materialize.min.css" "cljsjs/production/materialize.min.inc.css"
	     })
    (sift :include #{#"^cljsjs"})
    (deps-cljs :name "cljsjs.materializecss"
        :requires ["cljsjs.jquery-ui"])))
