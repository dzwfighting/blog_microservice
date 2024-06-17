FROM eclipse-temurin:17

LABEL mentainer="evan@gmail.com"

WORKDIR /app

COPY target/category-service-0.0.1-SNAPSHOT.jar /app/category-service.jar

EXPOSE 8084

ENTRYPOINT ["java", "-jar", "category-service.jar"]