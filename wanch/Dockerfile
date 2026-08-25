# Step 1: Build the JAR
FROM eclipse-temurin:21-jdk AS build
WORKDIR /app
COPY . .

# Grant execute permissions
RUN chmod +x ./gradlew

# Build project skipping tests
RUN ./gradlew build -x test

# Step 2: Run the application
FROM eclipse-temurin:21-jre
WORKDIR /app

# Safely copy the single JAR produced
COPY --from=build /app/build/libs/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]