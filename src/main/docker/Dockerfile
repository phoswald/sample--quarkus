FROM eclipse-temurin:20-jre-alpine

COPY --chown=daemon:daemon target/quarkus-app /usr/local/application

USER daemon:daemon
EXPOSE 8080

WORKDIR /usr/local/application
CMD ["java", "-jar", "quarkus-run.jar"]
