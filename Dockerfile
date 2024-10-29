# Use the official OpenJDK base image from Docker Hub
FROM openjdk:17-jdk-alpine

# Set the working directory in the container
WORKDIR /app

# Add the JAR file from the target directory into the container
COPY tp-foyer-5.0.0.jar tp-foyer.jar

# Expose the port that your Spring Boot application runs on
EXPOSE 8089

# Command to run the application
ENTRYPOINT ["java", "-jar", "tp-foyer.jar"]
