# Build stage
FROM maven:3.8.6-eclipse-temurin-17-alpine AS build
WORKDIR /app

# Copy pom.xml and download dependencies
COPY pom.xml .
RUN mvn dependency:go-offline -B

# Copy source code and build
COPY src ./src
RUN mvn clean package -DskipTests

# Runtime stage
FROM eclipse-temurin:17-jre-alpine
WORKDIR /app

# Copy the JAR from build stage
COPY --from=build /app/target/movie-api-1.0.0.jar app.jar

# Expose port (Render will override with PORT env var)
EXPOSE 8000

# Run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
