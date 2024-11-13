# PlayerDataService

PlayerDataService is a Spring Boot RESTful microservice designed to manage player information, primarily handling CRUD operations on player data stored in an H2 in-memory database. This service loads player information from a CSV file at startup and validates the data before saving it in the database.

## Table of Contents
- [Features](#features)
- [Project Structure](#project-structure)
- [Installation](#installation)
- [Running the Application](#running-the-application)
- [Packaging with Docker](#packaging-with-docker)
- [API Endpoints](#api-endpoints)
- [Configuration](#configuration)
- [Built With](#built-with)
- [License](#license)

## Features
- **REST API Endpoints** to retrieve player data.
- **CSV Data Import**: Loads player data from a CSV file on application startup.
- **Data Validation**: Ensures player data meets constraints like positive values and valid date sequences.
- **Logging**: Provides insights into application activity.
- **Database**: Uses an in-memory H2 database, which is created and populated at runtime.
- **Test Coverage**: Integrated JaCoCo for test coverage reporting.

## Project Structure
```plaintext
src/
├── main/
│   ├── java/com/playerdata/playerdataservice/
│   │   ├── controller/      # REST controller handling player data endpoints
│   │   ├── model/           # Player entity model with validation constraints
│   │   ├── service/         # Business logic and data loading from CSV
│   │   ├── repository/      # Repository for database interactions
│   │   ├── util/            # Utility classes for CSV parsing
│   │   └── exception/       # Custom exceptions for handling not-found cases
│   └── resources/
│       └── application.properties  # Configuration properties for database and other settings
└── test/
    └── java/com/playerdata/playerdataservice/  # Unit and integration tests
```

## Installation
### Prerequisites
- Java 21 or higher
- Maven 3.8.1 or higher

## Running the Application
To start the application, run:
```bash
mvn spring-boot:run
```
The service will be accessible at `http://localhost:8080`.

> **Note**: Ensure that a CSV file named `player.csv` is present in the root directory to load player data upon startup.
## Packaging with Docker
You can build and run the application using Docker as follows:

### Building the Docker Image
To build the Docker image, navigate to the project root and run:
```bash
docker build -t playerdataservice .
```

### Running the Docker Container
To start the application inside a Docker container, run:
```bash
docker run -p 8080:8080 playerdataservice
```
The application will be accessible at `http://localhost:8080`.

## API Endpoints

### Get All Players
- **URL**: `/api/players`
- **Method**: `GET`
- **Description**: Returns a list of all players.
- **Response**: `200 OK` with JSON array of player objects.

### Get Player by ID
- **URL**: `/api/players/{playerID}`
- **Method**: `GET`
- **Description**: Retrieves a player by their unique `playerID`.
- **Response**:
    - `200 OK` with player data if found.
    - `404 Not Found` if player does not exist.

### Health Check
- **URL**: `/actuator/health`
- **Method**: `GET`
- **Description**: Checks the health status of the application.
- **Response**: `200 OK` with JSON object indicating the health status.

## Configuration
You can modify the application properties in `src/main/resources/application.properties`. Key configurations include:
- **H2 Database Console**: Accessible at `http://localhost:8080/h2-console` for in-memory database management.
- **JaCoCo Coverage**: Configured in the `pom.xml` for generating coverage reports.

## Built With
- [Spring Boot](https://spring.io/projects/spring-boot) - Java-based framework for building web applications.
- [H2 Database](https://www.h2database.com/) - In-memory database for testing.
- [OpenCSV](http://opencsv.sourceforge.net/) - Library for reading CSV files.
- [JaCoCo](https://www.jacoco.org/) - Code coverage library for Java.
