# Graylog

## Avoid low disk problem with ElasticSearch:

```
curl -XPUT -H "Content-Type: application/json" http://localhost:9200/_cluster/settings -d '{ "transient": { "cluster.routing.allocation.disk.threshold_enabled": false } }'
```

## Test GELF message:

```
curl -XPOST http://127.0.0.1:12201/gelf -p0 -d '{"short_message":"Hello there", "host":"example.org", "facility":"test", "_foo":"bar"}'                                    
```

## Start application:

```
./start.sh
```
