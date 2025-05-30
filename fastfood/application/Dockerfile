# Step 1: Build application with Maven
FROM maven:3.9.6-eclipse-temurin-17 AS build

WORKDIR /app

COPY .. .

RUN mvn clean package -DskipTests

# Step 2: Final Image
FROM eclipse-temurin:17-jdk-alpine
WORKDIR /app

# Copy .jar generated to the image final
COPY --from=build /application/target/fastfood-1.0-SNAPSHOT.jar app.jar

# Expose Spring Boot port
EXPOSE 8080

# Execute command application
ENTRYPOINT ["java", "-jar", "app.jar"]