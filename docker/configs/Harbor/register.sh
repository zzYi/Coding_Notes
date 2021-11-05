echo '{"insecure-registries":["172.17.0.1"]}' > /etc/docker/daemon.json && \
cat /etc/docker/daemon.json && \
systemctl restart docker && \
docker login -u USER -p 'PASSWORD' 172.17.0.1