
FROM eclipse-temurin:21-jdk-alpine

WORKDIR /app

COPY target/*.jar app.jar

EXPOSE 8002

ENV SPRING_PROFILES_ACTIVE=docker

ENTRYPOINT ["java", "-jar", "app.jar"]
