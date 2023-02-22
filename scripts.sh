#nginx
ab -n 100000 -c 1000 http://127.0.0.1/server_status
#application web endpoint
 ab -n 1000 -c 100 'http://127.0.0.1:8080/hello'
 #mongodb
 ab -n 1000 -c 100 'http://127.0.0.1:8080/api/create_doc/?collectionName=test_collection'
 #elasticsearch
 ab -n 1000 -c 100 'http://127.0.0.1:8080/api/create_elastic/?indexName=users_index'

