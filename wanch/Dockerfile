# Step 1: Build using official Gradle image (uses global 'gradle')
FROM gradle:8.5-jdk21 AS build
WORKDIR /app
COPY . .

# Uses installed 'gradle' instead of local './gradlew' script
RUN gradle build -x test

# Step 2: Run the Application
FROM eclipse-temurin:21-jre
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]