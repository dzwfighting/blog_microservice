FROM eclipse-temurin:17

LABEL mentainer="evan@gmail.com"

WORKDIR /app

COPY target/comment-service-0.0.1-SNAPSHOT.jar /app/comment-service.jar

EXPOSE 8083

ENTRYPOINT ["java", "-jar", "comment-service.jar"]