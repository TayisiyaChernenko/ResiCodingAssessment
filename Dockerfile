# Use OpenJDK 17 as the base image
FROM openjdk:17-jdk-slim

# Set working directory
WORKDIR /app

# Create necessary directories
RUN mkdir -p lib bin/classes src/main/resources/images

# Copy the project files
COPY src src/
COPY lib lib/

# Copy resource files explicitly
COPY src/main/resources/dates.txt src/main/resources/

# Compile the Java files, including resources
RUN javac -d bin/classes -cp ".:lib/*" src/main/java/Main.java src/main/java/DateNormalization.java src/main/java/MarsRoverClient.java
RUN cp -r src/main/resources/* bin/classes/

# Create a volume for the images directory
VOLUME ["/app/src/main/resources/images"]

# Set the entry point with src/main/resources in classpath
ENTRYPOINT ["java", "-cp", "bin/classes:lib/*:src/main/resources", "main.java.Main"] 