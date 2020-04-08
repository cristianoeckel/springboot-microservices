## About
This project was made to show my knowledge in Spring Boot microservices.

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

## Building
To compile this projects run:
```shell script
mvn clean compile
```
To test this projects run:
```shell script

## API Usage
Below are the API endpoints to be called, replace the <DATA> with the apropriate value.

- Get a list of cities from a state:
```shell script
curl --header "Content-Type: application/json" \
  --request GET \
  http://localhost:8080/v1/cities?state=
```

- Get a list of cities, can be filtered using query parameters 'name' and 'state', where 'name' is the name of the city, and state can be the complete name of the state, an abbreviation or the state's ID.
```shell script
curl --header "Content-Type: application/json" \
  --request GET \
  http://localhost:8080/v1/cities
```

- Get a city by ID;
```shell script
curl --header "Content-Type: application/json" \
  --request GET \
  http://localhost:8080/api/v1/cities/<CITY_ID>
```

- Create a new city;
```shell script
curl --header "Content-Type: application/json" \
  --request POST \
  --data '{ "name": "<NAME>", "state": <STATE> }' \
  http://localhost:8080/v1/cities
```

- Get a client by ID:
```shell script
curl --header "Content-Type: application/json" \
  --request GET \
  http://localhost:8080/v1/clients/id
```

- Get a client by name:
```shell script
curl --header "Content-Type: application/json" \
  --request GET \
  http://localhost:8080/api/v1/clients?name="client_name"
```

- Create a new client:
```shell script
curl --header "Content-Type: application/json" \
  --request POST \
  --data '{ "name": "<CUSTOMER_NAME>", "genre": "<MALE,FEMALE,UNDEFINED>", "birthday": "<DATE>",  "City": <CITY>}' \
  http://localhost:8080/v1/client
```

- Update client's name:
```shell script
curl --header "Content-Type: application/json" \
  --request POST \
  --data '{ "name": "<name>"}' \
  http://localhost:8080/v1/clients/<ID>
```

- Delete a client:
```shell script
curl --header "Content-Type: application/json" \
  --request DELETE \
  http://localhost:8080/api/v1/clients/<ID>
```
