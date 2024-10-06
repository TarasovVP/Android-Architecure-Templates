FROM openjdk:17-jdk-slim

WORKDIR /app

COPY server/build/libs/server-all /app/ktor-server.jar

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "/app/app.jar"]
