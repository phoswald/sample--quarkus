FROM ubuntu:latest

WORKDIR /usr/local/application

RUN chown 1:1 /usr/local/application \
 && chmod 755 /usr/local/application
COPY --chown=1:1 target/*-runner /usr/local/application/sample-quarkus

EXPOSE 8080
USER 1:1

# CMD ["/usr/local/application/sample-quarkus", "-Dquarkus.http.host=0.0.0.0"]
