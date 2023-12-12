# Stage 1: Build the application
FROM gradle:latest AS build
WORKDIR /app

COPY build.gradle.kts .
COPY settings.gradle.kts .
COPY src src

USER root
RUN chown -R gradle /app

USER gradle
RUN gradle clean build --no-daemon

# Stage 2: Create a minimal runtime image
FROM openjdk:17-jdk-alpine

WORKDIR /app

# Copy the JAR file from the build stage
COPY --from=build /app/build/libs/*.jar app.jar

EXPOSE 8080

# Specify the default command to run when the container starts
CMD ["java", "-jar", "app.jar"]
