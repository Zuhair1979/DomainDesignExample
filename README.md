# Code Challenge


Tech Stack

- Java 17
- Spring Boot 3.2+
- Spring Data JPA
- PostgreSQL
- Apache Commons CSV
- Spring Profiles (`csv`, `postgres`)
- REST API (via `@RestController`)
- Maven





1- install docker desktop

2- run docker compose up -d in infra folder. this will run postgres container

3- we have two profiles to run postgres/ csv. each profile run either csv controller and related classes or postgres

4- to choose which profile to run, in application.yml change below as your desire

spring:
  application:
    name: "filemanagement"
  profiles:
    active: csv   # you can switch between profiles postgres , csv

5- if you choose csv profile please setup the below environment variable in windows 
   CSV_FILE_PATH
   CSV_SEPARATOR
   CSV_COL
   CASH_SIZE

  Or if you fine with default, then keep it 
  file-path: ${CSV_FILE_PATH:D:/CodeChallenge/csvfolder/sample-input.csv}
  separator: ${CSV_SEPARATOR:,}
  columns: ${CSV_COL:4}
  cachsize: ${CASH_SIZE:11}

6- run filemanagement service first , then apigateway

7- for csv profile, we have tests for services and controller but not Integration as there was no enough time.
8- for csv profile below are the api end points
    GET localhost:8082/csv/persons
    GET localhost:8082/csv/persons/color/GRÜN
    GET localhost:8082/csv/persons/7
    POST localhost:8082/csv/persons/new
    {
	"firstName": "Lia",
	"lastName": "Afg",
	"address": "Moldova",
	"color": "ROT",
	"id": 1
    }
9- for csv profile only, we have LRU cach, but not for postgres.

10- for postgres profile, below are the Apis end points
    GET localhost:8082/postgres/db/persons
    GET localhost:8082/postgres/db/persons/2
    GETlocalhost:8082/postgres/db/persons/color/blau
    POST localhost:8082/postgres/db/persons/new
{
	"firstName": "Zuhier",
	"lastName": "USA",
	"address": "Germany",
	"color": "BLAU",
	"id": 1
}

11- Proper error handling with GlobalExceptionHandler

12- com.assessor.filemanagement
├── builder        // Person builders/validators
├── controller     // REST API endpoints
├── dto            // DTOs & error response
├── entity         // Data models for CSV & DB
├── exceptions     // Custom exception handling
├── mappers        // Directional mappers
├── repository     // JPA repository
├── service        // Business logic layer
└── configuration  // App configuration and caching


