docker run -d \
    -p 8000:8000 \
    -p 9002:9000 \
    --name=portainer \
    --restart=always \
    -v /var/run/docker.sock:/var/run/docker.sock:z \
    -v /home/portainer_data:/data:z \
    portainer/portainer-ce

docker run -d \
    -p 9001:9001 \
    --name portainer_agent \
    --restart=always \
    -v /var/run/docker.sock:/var/run/docker.sock:z \
    -v /var/lib/docker/volumes:/var/lib/docker/volumes:z \
    portainer/agent