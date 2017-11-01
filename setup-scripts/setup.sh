#!/usr/bin/env bash
echo 'Starting Cassandra...'
docker rm -f tgt-case-study-cassandra
docker run -d --name tgt-case-study-cassandra -p7000:7000 -p7001:7001 -p9042:9042 -p9160:9160 cassandra
#remove dangling volumes
docker volume ls -qf dangling=true | xargs docker volume rm
echo 'waiting for startup...'
sleep 1
(docker logs -f --tail 1 tgt-case-study-cassandra &) | grep -m1 'Created default superuser role '
echo 'initializing...'
docker cp setup_cassandra.cql tgt-case-study-cassandra:/tmp/setup_cassandra.cql
docker exec -i tgt-case-study-cassandra cqlsh -f /tmp/setup_cassandra.cql
echo 'All Done!'