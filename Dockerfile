# Use OpenJDK 11 as the base image
FROM openjdk:11-jdk-slim

# Expose the application's port
EXPOSE 8082

# Add the jar file to the container
ADD target/tp-foyer-1.0.jar tp-foyer-1.0.jar

# Specify the entry point to run the jar
ENTRYPOINT ["java", "-jar", "/tp-foyer-1.0.jar"]
