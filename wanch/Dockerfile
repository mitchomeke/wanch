FROM gradle:8.5-jdk21 AS build
WORKDIR /app
COPY . .

# Change directory to where build.gradle actually lives before running gradle
WORKDIR /app/wanch
RUN gradle build -x test

FROM eclipse-temurin:21-jre
WORKDIR /app
# Copy the jar from the subfolder build directory
COPY --from=build /app/wanch/build/libs/*.jar app.jar

EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]