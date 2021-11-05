# Gitlab
docker run \
    -d --name gitlab \
    --restart always \
    -p 443:443 \
    -p 80:80 \
    -p 222:22 \
    -v /home/gitlab/config:/etc/gitlab:Z \
    -v /home/gitlab/logs:/var/log/gitlab:Z \
    -v /home/gitlab/data:/var/opt/gitlab:Z gitlab/gitlab-ee
    
13.12.12 → 14.0.11 → 14.1.7 → 14.2.5 -> latest

# Manually remove the Redis dump
docker exec -it gitlab gitlab-ctl stop && docker stop gitlab && docker rm gitlab && sudo rm -r /home/gitlab/logs/* && docker images