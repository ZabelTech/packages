(set-env!
  :resource-paths #{"resources"}
  :dependencies '[[adzerk/bootlaces   "0.1.9" :scope "test"]
                  [cljsjs/boot-cljsjs "0.4.6" :scope "test"]
		  [cljsjs/jquery "2.1.4-0"]
		  [cljsjs/jquery-ui "1.11.3-1"]])

(require '[adzerk.bootlaces :refer :all]
         '[cljsjs.boot-cljsjs.packaging :refer :all])

(def +version+ "0.97.0")

(task-options!
  pom  {:project     'cljsjs/materializecss
        :version     +version+
        :description "Materialize Css Framework"
        :url         "http://materializecss.com/"
        :license     {"MIT" "http://opensource.org/licenses/MIT"}
        :scm         {:url "https://github.com/cljsjs/packages"}})

(deftask package []
  (comp
    (download :url "https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.0/js/materialize.js"
              :checksum "FFE1E2EB44EFBF279AFDCB0911EED217")
    (download :url "https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.0/js/materialize.min.js"
              :checksum "93BE6CCFAED4778714CD3256C1B9CA4C")
    (download :url "https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.0/css/materialize.css"
              :checksum "3FD74DC11EE0143B4DDB5578158D7B41")
    (download :url "https://cdnjs.cloudflare.com/ajax/libs/materialize/0.97.0/css/materialize.min.css"
              :checksum "71C17718379307C36C68843836245717")

(sift :move {#"materialize.js" "cljsjs/development/materialize.inc.js"
             #"materialize.min.js" "cljsjs/production/mazerialize.min.inc.js"
	     #"materialize.css" "cljsjs/development/materialize.inc.css"
	     #"materialize.min.css" "cljsjs/production/materialize.min.inc.css"
	     })
    (sift :include #{#"^cljsjs"})
    (deps-cljs :name "cljsjs.materializecss"
        :requires ["cljsjs.jquery-ui"])))
