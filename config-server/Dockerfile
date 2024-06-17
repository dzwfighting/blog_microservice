FROM eclipse-temurin:17

LABEL mentainer="evan@gmail.com"

WORKDIR /app

COPY target/config-server-0.0.1-SNAPSHOT.jar /app/config-server.jar

EXPOSE 8087

ENTRYPOINT ["java", "-jar", "config-server.jar"]