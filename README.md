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
  -jar target/quarkus-app/quarkus-run.jar
~~~

~~~
$ mvn compile quarkus:dev \
  -Dquarkus.datasource.jdbc.url=jdbc:h2:./databases/task-db
~~~

## Run Über-Jar

~~~
$ mvn clean verify -Dquarkus.package.type=uber-jar
$ java \
  -Dquarkus.http.port=8080 \
  -Dquarkus.datasource.jdbc.url=jdbc:h2:./databases/task-db \
  -jar target/sample-quarkus-0.1.0-SNAPSHOT-runner.jar
~~~

## Run Natively

~~~
$ mvn clean verify -Pnative -Dquarkus.native.container-build=true \
  -Dquarkus.datasource.jdbc.url=jdbc:h2:tcp://localhost/./task-db
$ target/sample-quarkus-0.1.0-SNAPSHOT-runner
~~~

Note: JVM System Properties can be passed as normal command line arguments (using `-D...=...` syntax).

Verify native executable dependencies:

~~~
$ ldd target/sample-quarkus-0.1.0-SNAPSHOT-runner
	linux-vdso.so.1 (0x00007ffcf05f6000)
	libstdc++.so.6 => /lib/x86_64-linux-gnu/libstdc++.so.6 (0x00007fd631a6a000)
	libpthread.so.0 => /lib/x86_64-linux-gnu/libpthread.so.0 (0x00007fd631a47000)
	libdl.so.2 => /lib/x86_64-linux-gnu/libdl.so.2 (0x00007fd631a41000)
	libz.so.1 => /lib/x86_64-linux-gnu/libz.so.1 (0x00007fd631a25000)
	librt.so.1 => /lib/x86_64-linux-gnu/librt.so.1 (0x00007fd631a1a000)
	libc.so.6 => /lib/x86_64-linux-gnu/libc.so.6 (0x00007fd631828000)
	libm.so.6 => /lib/x86_64-linux-gnu/libm.so.6 (0x00007fd6316d7000)
	/lib64/ld-linux-x86-64.so.2 (0x00007fd631c63000)
	libgcc_s.so.1 => /lib/x86_64-linux-gnu/libgcc_s.so.1 (0x00007fd6316bc000)
~~~

## Run in Docker

~~~
$ docker build -f src/main/docker/Dockerfile -t sample-quarkus .
$ docker run -it --rm --name my-quarkus -p 8080:8080 sample-quarkus \
  /usr/local/application/sample-quarkus \
  -Dquarkus.http.host=0.0.0.0 \
  -Dquarkus.datasource.jdbc.url=jdbc:h2:tcp://192.168.1.118/./task-db
~~~

Note: JVM System Properties can be passed as normal command line arguments (using `-D...=...` syntax).

## Run H2 Server:

Running H2 in server mode is required when using Quarkus' native mode
(because H2's embedded mode is not supported in native mode).

~~~
$ cd ~/code/databases
$ java -cp ~/.m2/repository/com/h2database/h2/2.1.210/h2-2.1.210.jar org.h2.tools.Server -tcpAllowOthers
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
