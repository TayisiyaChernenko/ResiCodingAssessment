# Use OpenJDK 17 as the base image
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Copy the project files
COPY . .

# Create necessary directories
RUN mkdir -p lib src/main/resources/images

# Download the JSON library
RUN curl -L https://repo1.maven.org/maven2/org/json/json/20231013/json-20231013.jar -o lib/json-20231013.jar

# Compile the Java files
RUN javac -d bin/classes -cp ".:lib/*" src/main/java/Main.java src/main/java/DateNormalization.java src/main/java/MarsRoverClient.java

# Set the entry point
ENTRYPOINT ["java", "-cp", "bin/classes:lib/*", "main.java.Main"] 