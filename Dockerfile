FROM eclipse-temurin:17-alpine
WORKDIR /app
COPY target/MovieDatabaseAPI-0.0.1-SNAPSHOT.jar moviedatabase.jar
EXPOSE 8080
CMD ["java", "-jar", "moviedatabase.jar"]