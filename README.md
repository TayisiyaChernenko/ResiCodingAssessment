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

#### Using the .env file (Recommended)

1. Make sure your NASA API key is in the .env file:
```bash
# Check the current .env file
cat .env
# If needed, update it with your key
echo "NASA_API_KEY=your_key_here" > .env
```

2. Run the program using the .env file:
```bash
source .env && java -cp "bin/classes:lib/*" main.java.Main
```

#### Using Environment Variable

Alternatively, you can set the API key directly:
```bash
export NASA_API_KEY=your_key_here
java -cp "bin/classes:lib/*" main.java.Main
```

## Troubleshooting

If you encounter issues:

1. Check your API key:
```bash
# Verify the .env file contents
cat .env
# Or check the environment variable
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


