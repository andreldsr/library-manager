# Step 1: Build the app
FROM ghcr.io/graalvm/native-image-community:21 as build

WORKDIR /home/gradle/project

# Copy Gradle files
COPY build.gradle.kts settings.gradle.kts gradlew gradlew.bat ./
COPY gradle ./gradle

RUN microdnf install findutils

RUN chmod +x gradlew
# Download dependencies
RUN ./gradlew dependencies --stacktrace

# Copy all project files
COPY . .

# Build the app
RUN ./gradlew nativeCompile

# Step 2: Create minimal runtime image
FROM alpine:latest

WORKDIR /home/app

COPY --from=build /home/gradle/project/build/native/nativeCompile .

EXPOSE 8080

RUN apk add gcompat

CMD ["./library-manager"]