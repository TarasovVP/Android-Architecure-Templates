FROM openjdk:17-jdk-slim

WORKDIR /app

COPY server/build/libs/ktor-server-1.0.0.jar /app/ktor-server.jar

RUN curl -o cloud_sql_proxy https://dl.google.com/cloudsql/cloud_sql_proxy.linux.amd64 && chmod +x cloud_sql_proxy

EXPOSE 8080

CMD ["./cloud_sql_proxy", "-dir=/cloudsql", "-instances=android-architecture-templates:us-central1:android-architecture-templates-db", "&", "java", "-jar", "/app/ktor-server.jar"]

ENTRYPOINT ["java", "-jar", "/app/ktor-server.jar"]
