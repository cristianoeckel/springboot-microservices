## About
This project was made to show my knowledge in Spring Boot microservices.

## Documentation
To acess the API documentation you can go to
CITYMS-Documentation
http://localhost:8080/swagger-ui.html/
CLIENTMS-Documentation
http://localhost:8081/swagger-ui.html/

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
Execute this command -> newman run tests/apitests.postman_collection.json -e tests/teste.postman_environment.json
on the root folder where you cloned the project.

## Building
To compile this projects run:
```shell script
mvn clean compile
```
## API Usage
Below are the API endpoints to be called, replace the <DATA> with the apropriate value.

- Get a list of cities from a state:
```shell script
curl --header "Content-Type: application/json" \
  --request GET \
  http://localhost:8080/cityms/v1/cities?state=
```

- Get a city by name;
```shell script
curl --header "Content-Type: application/json" \
  --request GET \
  http://localhost:8080/cityms/v1/cities?name=<CITY_ID>
```

- Register's a new city;
```shell script
curl --header "Content-Type: application/json" \
  --request POST \
  --data '{ "name": "<NAME>", "state": <STATE> }' \
  http://localhost:8080/cityms/v1/cities
```

- Get a client by ID:
```shell script
curl --header "Content-Type: application/json" \
  --request GET \
  http://localhost:8081/clientms/v1/clients/id
```

- Get a client by name:
```shell script
curl --header "Content-Type: application/json" \
  --request GET \
  http://localhost:8081/clientms/v1/clients?name="client_name"
```

- Register's a new client:
```shell script
curl --header "Content-Type: application/json" \
  --request POST \
  --data '{ "name": "<CUSTOMER_NAME>", "genre": "<MALE,FEMALE,UNDEFINED>", "birthday": "<DATE>",  "City": <CITY>}' \
  http://localhost:8081/clientms/v1/clients
```

- Update client's name:
```shell script
curl --header "Content-Type: application/json" \
  --request PUT \
  --data '{ "name": "<name>"}' \
  http://localhost:8081/clientms/v1/clients/<ID>
```

- Delete a client:
```shell script
curl --header "Content-Type: application/json" \
  --request DELETE \
  http://localhost:8081/clientms/v1/clients/<ID>
```
