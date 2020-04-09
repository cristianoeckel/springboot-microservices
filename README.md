## About
This project was made to show my knowledge in Spring Boot microservices.

## Documentation
After executing the project, to acess the API documentation you can go to

cityms-documentation
http://localhost:8080/swagger-ui.html/

clientms-Documentation
http://localhost:8081/swagger-ui.html/

# Building

You can run this project on your ECLIPSE IDE,
or you can manually run each jar file.

To generate an executable jar file you can run the following command:

```mvn clean install```

You have to configure you database manually on applcation properties. The project will use the following default databases: cityms and clientms both using username:postgres and password:postgres you can create the databases with the commands below.

```create database city;```
```create database customer;```

To run the project, compile and package all of the five project's then acess the "target" dir and execute the following commands:

```
java -jar config-server.jar
java -jar eureka-server.jar
java -jar zuul.jar
java -jar cityms-1.0.0.jar
java -jar clientms-1.0.0.jar
```

### city-service
- Service that returns and saves data about States and Cities;
- Uses PostgreSQL as database;

### client-service
- Service that returns and saves data about Clients
- Uses PostgreSQL;
- Uses Feign client to request data from other APIs.

## Prerequisites
To compile this software maven should be installed.
To run this software, a JRE and a PostgreSQL.
To test this project, node v10> and newman.

## Running tests
Execute this command:
```newman run tests/apitests.postman_collection.json -e tests/teste.postman_environment.json```
on the root folder where you cloned the project.

## API Usage
Below are the API endpoints to be called.

- Get a list of cities from a state:
```shell script
curl --header "Content-Type: application/json" \
  --request GET \
  http://localhost:8080/cityms/v1/cities?state="state"
```

- Get a city by name;
```shell script
curl --header "Content-Type: application/json" \
  --request GET \
  http://localhost:8080/cityms/v1/cities?name="name"
```

- Register's a new city;
```shell script
curl --header "Content-Type: application/json" \
  --request POST \
  --data '{ "name": "Candelária", "state": "RS"> }' \
  http://localhost:8080/cityms/v1/cities
```

- Get a client by ID:
```shell script
curl --header "Content-Type: application/json" \
  --request GET \
  http://localhost:8081/clientms/v1/clients/{id}
```

- Get a client by name:
```shell script
curl --header "Content-Type: application/json" \
  --request GET \
  http://localhost:8081/clientms/v1/clients?name="name"
```

- Register's a new client:
```shell script
curl --header "Content-Type: application/json" \
  --request POST \
  --data '{ "name": "Cristiano", "genre": "<MALE,FEMALE,UNDEFINED>", "birthday": "1999-01-01",  "City": <CITY>}' \
  http://localhost:8081/clientms/v1/clients
```

- Update client's name:
```shell script
curl --header "Content-Type: application/json" \
  --request PUT \
  --data '{ "name": "name"}' \
  http://localhost:8081/clientms/v1/clients/{id}
```

- Delete a client:
```shell script
curl --header "Content-Type: application/json" \
  --request DELETE \
  http://localhost:8081/clientms/v1/clients/{id}
```
