# Use the official OpenJDK base image from Docker Hub
FROM openjdk:17-jdk-alpine

# Expose the port that your Spring Boot application runs on
EXPOSE 8089

# Add the JAR file from the target directory into the container
ADD target/tp-foyer-1.0.0.jar tp-foyer.jar

# Command to run the application
ENTRYPOINT ["java", "-jar", "tp-foyer.jar"]
