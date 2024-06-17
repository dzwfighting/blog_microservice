FROM eclipse-temurin:17

LABEL mentainer="evan@gmail.com"

WORKDIR /app

COPY target/user_service-0.0.1-SNAPSHOT.jar /app/user-service.jar

ENTRYPOINT ["java", "-jar", "user-service.jar"]