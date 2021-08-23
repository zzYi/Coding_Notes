# Elasticsearch
docker run \
    -d --name ES \
    --restart=always \
    -p 9200:9200 -p 9300:9300 \
    -v /home/elasticsearch/data:/usr/share/elasticsearch/data:Z \
    -v /home/elasticsearch/plugins:/usr/share/elasticsearch/plugins:Z \
    -v /home/elasticsearch/config:/usr/share/elasticsearch/config:Z \
    -v /home/elasticsearch/logs:/usr/share/elasticsearch/logs:Z \
    -e "discovery.type=single-node" \
    docker.elastic.co/elasticsearch/elasticsearch:<version>

# Kibana
docker run \
    -d --name kibana \
    --restart=always \
    -p 5601:5601 \
    --link ES:elasticsearch \
    -v /home/kibana/config:/usr/share/kibana/config:Z \
    -v /home/kibana/data:/usr/share/kibana/data:Z \
    -v /home/kibana/plugins:/usr/share/kibana/plugins:Z \
    docker.elastic.co/kibana/kibana:<version>