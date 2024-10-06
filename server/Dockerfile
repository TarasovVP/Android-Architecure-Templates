FROM openjdk:17-jdk-slim

WORKDIR /app

COPY server/build/libs/ktor-server-1.0.0.jar /app/ktor-server.jar

ENTRYPOINT ["java", "-jar", "/app/ktor-server.jar"]
