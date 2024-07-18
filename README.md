# Clustered Data Warehouse API Documentation


## Project Overview

The `ClusteredDataWarehouse` project is a part of a data warehouse solution developed for Bloomberg to analyze FX (Foreign Exchange) deals. The primary function of this project is to accept deal details and persist them into a database.

## Technologies Used

- Java
- Spring Boot
- Spring Data JPA
- PostgreSQL
- Docker
- Maven
- Make File

## Features

- Accepts deal details through a REST API.
- Validates the structure of the deal data.
- Ensures unique deal entries to avoid duplication.
- Persists validated deals into a PostgreSQL database.
- Proper error/exception handling and logging.
- Includes unit tests with high coverage.
- Check the ISO code if correct


## API Endpoint

### Endpoint:
- **[POST] http://localhost:8080/api/deals**
- **[GET] http://localhost:8080/api/deals/data**

### Request JSON Example:
```json
{
    "dealUniqueId": "125Fg",
    "fromCurrencyIsoCode": "JOD",
    "toCurrencyIsoCode": "USD",
    "dealAmount": 52300
}
```

## Request Validation

The endpoint performs field validation using Jakarta Validation annotations. Any invalid field triggers a structured error message handled by the `RequestsStatusException` REST Controller Advice, responding with HTTP status `BAD_REQUEST`.

## Database Interaction

- If the FX deal with the specified `dealUniqueId` does not exist in the Postgres database:
  - The object is inserted.
  - The inserted object is returned.
  - A message is show up
  ```
        {
            "dealUniqueId": "125Fg",
            "message": "success"
        }
  ```

- If the FX deal with the specified `dealUniqueId` already exists in the database:
  - An exception is thrown with a message 
  ```
        {
            "error": "Deal is already there."
        }
  ```


## Testing

- **JUnit 5:**
  - The project employs JUnit 5 for unit testing.
  - Extensive test coverage ensures robustness, achieving 100% coverage.

- **Mockito:**
  - Mockito is utilized for efficient mocking in unit tests.

## Dockerization

The application is Dockerized using a multi-stage Dockerfile, optimizing the Docker image size and ensuring efficient deployment.

