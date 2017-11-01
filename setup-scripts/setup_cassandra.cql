CREATE KEYSPACE IF NOT EXISTS tgt_casestudy_pricing WITh replication = {'class':'SimpleStrategy','replication_factor' : 2};
use tgt_casestudy_pricing;
CREATE TABLE IF NOT EXISTS product_price (product_id int primary key, value double, currency_code varchar);
INSERT INTO product_price (product_id, value, currency_code) values (13860428, 13.49, 'USD');
INSERT INTO product_price (product_id, value, currency_code) values (15117729, 14.99, 'USD');
INSERT INTO product_price (product_id, value, currency_code) values (16483589, 15.99, 'USD');
INSERT INTO product_price (product_id, value, currency_code) values (16696652, 16.99, 'USD');
INSERT INTO product_price (product_id, value, currency_code) values (16752456, 17.99, 'USD');
INSERT INTO product_price (product_id, value, currency_code) values (15643793, 18.99, 'USD');
