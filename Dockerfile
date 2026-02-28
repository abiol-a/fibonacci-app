# This will use Maven with Java 21 to build the application
FROM maven:3.9-eclipse-temurin-21 AS builder
WORKDIR /app
COPY pom.xml .
RUN mvn dependency:go-offline -B

COPY src ./src
RUN mvn clean package -DskipTests

# This will use Eclipse Temurin for the runtime
FROM eclipse-temurin:21-jre-alpine
WORKDIR /app
# Use the JAR with dependencies (note the different filename)
COPY --from=builder /app/target/my-app-1.0-SNAPSHOT-jar-with-dependencies.jar app.jar
EXPOSE 8080
CMD ["java", "-jar", "app.jar"]