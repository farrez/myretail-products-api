# myRetail Products API - Shane Farrell

## Prerequisites
* Maven
* docker (https://www.docker.com/get-docker)

## Overview
This project shows a working solution to the "myRetail RESTful service" case study.  It includes functional tests that are executed when building the application, so it requires a docker container running Cassandra (with pre-populated price data).  

### Setup the Docker Container running Cassandra
You can run the setup-scripts/setup.sh script (from the main project directory) to start the required docker cassandra container and populate the datastore with price data.  This script will start a cassandra docker container in the background named 'tgt-case-study-cassandra'.
```
./setup-scripts/setup.sh
```
Once you are done running/testing the application you should kill and remove the docker container.
```
docker kill tgt-case-study-cassandra
docker rm tgt-case-study-cassandra
```

### Building the project 
You can use standard Maven build commands to build the application (which also executes both unit and functional test).
```
mvn clean install
```
### Run the app
Once the application has been built you can also run it using the spring boot run command for Maven.  Since the project is setup with submodules, you need to make sure to specify the module with the main application class. 
From the main project myretail-products-api directory:
```
mvn -pl myretail-products-api spring-boot:run
```
### See the application work:
When the application is running any GET request to http://localhost:8080/products/{id} where {id} is the product id should return data showing the name sourced from the given redsky endpoint and price data from the Cassandra datastore.

### Updating price data:
The application has an endpoint that allows for pricing updates via PUT method.  Here is an example on how to update the price of product id 16696652 with a value of 9.99 and currency code of CAD. 
```
curl -H "Content-Type: application/json" -X PUT -d '{"id":16696652,"name":"Beats Solo 2 Wireless - Black","current_price":{"value":9.99,"currency_code":"CAD"}}' http://localhost:8080/products/16696652
```
### Versions
The following versions were used:
* java version "1.8.0_111"
* Groovy Version: 2.4.7
* Apache Maven 3.5.0
* docker Client Version: 17.09.0-ce
* docker Client API version:  1.32
* docker Server Version: 17.09.0-ce
* docker Server API version:  1.32 (minimum version 1.12)
