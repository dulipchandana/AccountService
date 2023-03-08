# Account Service

This Spring Boot microservice is built to expose account api and transaction api .

## Description

This microservice can be used to get account details by user id  and get transaction details by account id.
Account  details with these details  (Account Number, Account Name, Account Type, Balance Data, Currency, Opening Available Balance).
* Logging has been implemented with AOP, LoggingAspect.java is responsible to write logs.
* All the rest error codes and messages handled with ControllerAdvisor.java.
* mysql has been used as the main db , h2 embed db has been used to support repository tests.
* initial data is stored in data.sql , if there will be  any changes , please run the  test suit to validate the data .

## Getting Started
The code base is structured with the following layers .
* advisor
* aspect
* config
* controller
* dto
* entity
* exceptions
* repository
* service
* util

### Dependencies
The following tools need to be installed.
* Java 11
* Docker
* Gradle

### Installing

* Spring Boot profiling has been used to the program.
* local profile can be used to run the program in local environment.
* dev and prod configurations has been added to the project.

### Executing program

* Gradle tasks has been implemented setup environment and run the application.
* Start the local environment. 
```
./gradlew startLocalEnvironment
```
* Stop the local environment.
```
./gradlew stopLocalEnvironment
```
* Start the application.
```
./gradlew bootrun
```

* Run the test.
```
./gradlew test
```

## Help

The Following command can be used to start test environment with Docker.
```
docker-compose up -d
```
Stop docker containers
```
docker-compose stop -d
```
Access the swagger-ui
```
curl http://localhost:8080/swagger-ui/
```
Access the account api
```
curl http://localhost:8080/api/users/1/accounts/
```
Access the transaction api
```
curl http://localhost:8080/api/accounts/1/transactions/
```
## Authors

Contributors names and contact info

[@Dulipchandana](https://www.linkedin.com/in/dulip-chandana-91286297/)

## Version History

* 1.2
    * Various bug fixes and optimizations
* 1.0.1-SNAPSHOT
    * Initial Release

