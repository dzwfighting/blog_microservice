FROM eclipse-temurin:17

LABEL mentainer="evan@gmail.com"

WORKDIR /app

COPY target/blogpost_service-0.0.1-SNAPSHOT.jar /app/blogpost-service.jar

EXPOSE 8082

ENTRYPOINT ["java", "-jar", "blogpost-service.jar"]