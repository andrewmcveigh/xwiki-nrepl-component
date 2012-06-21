XWiki nRepl Component
=====================
If you need to inject a Clojure nRepl into you XWiki instance, this will do it.

Building
--------
Assuming you have maven installed, run:
    
    mvn package

Running
-------
You must include the compiled .jar in XWiki's classpath E.G.,
    
    /usr/share/jetty/webapps/xwiki/WEB-INF/lib/
or

    /usr/share/jetty/lib/
    
Restart XWiki, or it's container.

Check it's Loaded
-----------------
From a wiki page the following velocity code should let you know if the component is loaded.

    {{velocity}}
    $services.xwiki_nrepl.cljGreet()
    {{/velocity}}

Run it!
-------
From a plugin/component:

Clojure

    (.startRepl
      (Utils/getComponent com.dirtybrussels.xwiki_nrepl_component.IXWikiNRepl)
      (java.util.HashMap {:context context
                          :object1 objectDiff}))

Java

    Utils.getComponent(com.dirtybrussels.xwiki_nrepl_component.IXWikiNRepl.class)
        .startRepl(HashMap);

Connect nRepl
-------------
The nRepl starts on port 7888. If you want to change the port, you can in core.clj.
From leiningen (with nRepl support) you can connect using:

    lein repl :connect hostname:7888
