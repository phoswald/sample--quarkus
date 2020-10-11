# sample-quarkus

Experiments with Quarkus, featuring:

- Static web content
- Dynamic web content using JAX-RS and Qute
- REST endpoints using JAX-RS 
- H2 database using JPA and Hibernate

## Run Standalone

~~~
$ mvn clean verify
$ export APP_SAMPLE_CONFIG=ValueFromShell
$ java \
  -Dquarkus.http.port=8080 \
  -Dquarkus.datasource.jdbc.url=jdbc:h2:./databases/task-db \
  -jar target/sample-quarkus-0.1.0-SNAPSHOT-runner.jar
~~~

## Run Natively

~~~
$ mvn clean verify -P native
$ export APP_SAMPLE_CONFIG=ValueFromShell
$ target/sample-quarkus-0.1.0-SNAPSHOT-runner
~~~

~~~
$ ldd target/sample-quarkus-0.1.0-SNAPSHOT-runner 
    linux-vdso.so.1 (0x00007fff35581000)
    libpthread.so.0 => /lib/x86_64-linux-gnu/libpthread.so.0 (0x00007fe19a7e0000)
    libdl.so.2 => /lib/x86_64-linux-gnu/libdl.so.2 (0x00007fe19a7da000)
    libz.so.1 => /lib/x86_64-linux-gnu/libz.so.1 (0x00007fe19a7be000)
    librt.so.1 => /lib/x86_64-linux-gnu/librt.so.1 (0x00007fe19a7b3000)
    libc.so.6 => /lib/x86_64-linux-gnu/libc.so.6 (0x00007fe19a5c1000)
    /lib64/ld-linux-x86-64.so.2 (0x00007fe19a81d000)
~~~

## URLs

- http://localhost:8080/

~~~
$ curl 'http://localhost:8080/app/rest/sample/time' -i
$ curl 'http://localhost:8080/app/rest/sample/config' -i
$ curl 'http://localhost:8080/app/rest/sample/echo-xml' -i -X POST \
  -H 'content-type: text/xml' \
  -d '<EchoRequest><input>This is CURL</input></EchoRequest>'
$ curl 'http://localhost:8080/app/rest/sample/echo-json' -i -X POST \
  -H 'content-type: application/json' \
  -d '{"input":"This is CURL"}'
$ curl 'http://localhost:8080/app/rest/tasks' -i
$ curl 'http://localhost:8080/app/rest/tasks' -i -X POST \
  -H 'content-type: application/json' \
  -d '{"title":"Some task","description":"This is CURL","done":true}'
$ curl 'http://localhost:8080/app/rest/tasks/5b89f266-c566-4d1f-8545-451bc443cf26' -i
$ curl 'http://localhost:8080/app/rest/tasks/5b89f266-c566-4d1f-8545-451bc443cf26' -i -X PUT \
  -H 'content-type: application/json' \
  -d '{"title":"Some updated task","description":"This is still CURL","done":false}'
$ curl 'http://localhost:8080/app/rest/tasks/5b89f266-c566-4d1f-8545-451bc443cf26' -i -X DELETE
~~~

## TODO

- Docker genauer anschauen
- Native Image genauer anschauen, funktioniert nicht im Embedded Modus von H2
