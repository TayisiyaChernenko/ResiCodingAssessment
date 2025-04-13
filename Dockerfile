# Use OpenJDK 17 as the base image
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Create necessary directories
RUN mkdir -p lib src/main/resources/images bin/classes

# Copy the project files
COPY src src/
COPY lib lib/

# Copy resource files explicitly
COPY src/main/resources/dates.txt src/main/resources/
COPY src/main/resources/images-demo/* src/main/resources/images/

# Compile the Java files, including resources
RUN javac -d bin/classes -cp ".:lib/*" src/main/java/Main.java src/main/java/DateNormalization.java src/main/java/MarsRoverClient.java
RUN cp -r src/main/resources/* bin/classes/

# Set the entry point with src/main/resources in classpath
ENTRYPOINT ["java", "-cp", "bin/classes:lib/*:src/main/resources", "main.java.Main"] 