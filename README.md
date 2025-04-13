# Mars Rover Photo Downloader

This project downloads photos from NASA's Mars Rover API for specified dates located in the dates.txt file.

## Prerequisites

- Docker

## Setup

1. Clone the repository:
```bash
git clone <repository-url>
cd ResiCodingAssessment
```

2. (Optional) Set your NASA API key:
```bash
export NASA_API_KEY=your_api_key_here
```

## Running with Docker

1. Build and run the container:
```bash
docker build -t mars-rover .
docker run -v $(pwd)/src/main/resources/images:/app/src/main/resources/images --env-file .env mars-rover
```

The program will:
- Read dates from `src/main/resources/dates.txt`
- Download Mars Rover photos for each valid date
- Save the photos to `src/main/resources/images/`

## Running Locally

If you prefer to run the program without Docker:

1. Make sure you have Java 17 or later installed
2. Download the JSON library:
```bash
mkdir -p lib
curl -L https://repo1.maven.org/maven2/org/json/json/20231013/json-20231013.jar -o lib/json-20231013.jar
```

3. Compile and run:
```bash
javac -d bin/classes -cp ".:lib/*" src/main/java/Main.java src/main/java/DateNormalization.java src/main/java/MarsRoverClient.java
java -cp "bin/classes:lib/*" main.java.Main
```

## Project Structure

- `src/main/java/` - Java source files
  - `Main.java` - Entry point
  - `DateNormalization.java` - Date parsing and formatting
  - `MarsRoverClient.java` - NASA API client
- `src/main/resources/` - Resource files
  - `dates.txt` - Input dates
  - `images/` - Downloaded photos after running the program
  - `images-demo` - Downloaded photos for viewing 
- `lib/` - External dependencies