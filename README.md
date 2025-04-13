# Mars Rover Photo Downloader

This project downloads photos from NASA's Mars Rover API for specified dates located in the dates.txt file.

## Prerequisites

- Docker (for Docker setup)
- Java 17 or later (for local running)

## Setup

1. Clone the repository:
```bash
git clone <repository-url>
cd ResiCodingAssessment
```

## Running with Docker (Recommended)

1. Build and run the container:
```bash
docker build -t mars-rover .
docker run -v $(pwd)/src/main/resources/images:/app/src/main/resources/images --env-file .env mars-rover
```

The program will:
- Read dates from `src/main/resources/dates.txt`
- Download Mars Rover photos for each valid date
- Save the photos to `src/main/resources/images/`

## Running Locally (Without Docker)

### Setup for Local Running

1. Create necessary directories:
```bash
mkdir -p bin/classes lib src/main/resources/images
```

2. Download the JSON library:
```bash
curl -L https://repo1.maven.org/maven2/org/json/json/20231013/json-20231013.jar -o lib/json-20231013.jar
```

3. Compile the Java files:
```bash
javac -d bin/classes -cp ".:lib/*" src/main/java/Main.java src/main/java/DateNormalization.java src/main/java/MarsRoverClient.java
```

### Running the Program

#### Using Environment Variable

You can set the API key directly:
```bash
export NASA_API_KEY=c534E5bnpF7UEtgrjrv7OUED4BgdsS7e9FPsPBDv
java -cp "bin/classes:lib/*" main.java.Main
```

## Troubleshooting

If you encounter issues:

1. Check your API key:
# Check the environment variable
echo $NASA_API_KEY
```

2. Verify the dates.txt file:
```bash
cat src/main/resources/dates.txt
```

3. Check directory permissions:
```bash
chmod 755 src/main/resources/images
```

4. If using DEMO_KEY, you might need to wait between runs due to rate limiting.


