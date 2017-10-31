## myRetail Products API

### Setup
To run the project, need to have Cassandra running with data pre-populated.  I chose to do this via docker.

### Install Cassandra via docker:
* download the Cassandra docker file
#### Run docker
docker run -it --rm --name cassandra-node1 -p7000:7000 -p7001:7001 -p9042:9042 -p9160:9160 cassandra

### Cassandra setup
Start a cql session via docker and execute the setup commands:
docker run -it --rm -e CQLSH_HOST=$(docker inspect --format='{{ .NetworkSettings.IPAddress }}' cassandra-node1) --name cassandra-client --entrypoint=cqlsh cassandra

cqlsh> CREATE KEYSPACE IF NOT EXISTS tgt_casestudy_pricing WITh replication = {'class':'SimpleStrategy','replication_factor' : 2};
cqlsh> use tgt_casestudy_pricing;
cqlsh:tgt_casestudy_pricing>CREATE TABLE IF NOT EXISTS product_price (product_id int primary key, value double, currency_code varchar);
cqlsh:tgt_casestudy_pricing>INSERT INTO product_price (product_id, value, currency_code) values (13860428, 13.49, 'USD');
cqlsh:tgt_casestudy_pricing>INSERT INTO product_price (product_id, value, currency_code) values (15117729, 14.99, 'USD');
cqlsh:tgt_casestudy_pricing>INSERT INTO product_price (product_id, value, currency_code) values (16483589, 15.99, 'USD');
cqlsh:tgt_casestudy_pricing>INSERT INTO product_price (product_id, value, currency_code) values (16696652, 16.99, 'USD');
cqlsh:tgt_casestudy_pricing>INSERT INTO product_price (product_id, value, currency_code) values (16752456, 17.99, 'USD');
cqlsh:tgt_casestudy_pricing>INSERT INTO product_price (product_id, value, currency_code) values (15643793, 18.99, 'USD');

### Build the Project and execute unit and functional tests
```
mvn clean install
```

### Run the app
Change directories to the myretail-products-api directory of the project (~/workspaces/myretail-products-api/myretail-products-api)
```
mvn spring-boot:run
```

### See the application work:
* http://localhost:8080/products/{i} from a browser

### curl commands for updating price:
curl -H "Content-Type: application/json" -X PUT -d '{"id":16696652,"name":"Beats Solo 2 Wireless - Black","current_price":{"value":9.99,"currency_code":"USD"}}' http://localhost:8080/products/16696652