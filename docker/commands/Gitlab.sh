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