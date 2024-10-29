# Use the official OpenJDK base image from Docker Hub
FROM openjdk:17-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file from the target directory into the container
COPY target/tp-foyer-5.0.0.jar tp-foyer.jar

# Expose the port that your Spring Boot application runs on
EXPOSE 8080

# Command to run the application
ENTRYPOINT ["java", "-jar", "/app/tp-foyer.jar"]
																																																		
