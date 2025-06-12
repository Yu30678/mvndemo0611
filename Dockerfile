FROM eclipse-temurin:21-jdk
WORKDIR /app
COPY target/demo0611-1.0-SNAP.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]