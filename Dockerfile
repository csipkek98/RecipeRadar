# # Use an official OpenJDK runtime as a parent image
# FROM mcr.microsoft.com/devcontainers/base:debian
FROM eclipse-temurin:21-jdk

# ARG DB_URL
# ARG DB_USERNAME
# ARG DB_PASSWORD

# ENV spring.datasource.url=$DB_URL
# ENV spring.datasource.username=$DB_USERNAME
# ENV spring.datasource.password=$DB_PASSWORD

RUN apt-get update && apt-get install -y curl unzip zip

# Set the working directory to /app
WORKDIR /app

# Copy the application JAR file to the container
COPY target/*.jar app.jar

# Expose port 8080 for the application to listen on
EXPOSE 8080

# Define the command to run the application
CMD ["java", "-jar", "app.jar"]

# # Define the command to run the application
# ENTRYPOINT ["java", "-jar", "app.jar"]