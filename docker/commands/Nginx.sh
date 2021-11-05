#Nginx
docker run -d \
    -p 80:80 \
    -p 443:443 \
    --restart always \
    --name nginx \
    -v /root/nginx/html:/usr/share/nginx/html:Z \
    -v /root/nginx/conf/nginx.conf:/etc/nginx/nginx.conf:Z \
    -v /root/nginx/conf/conf.d:/etc/nginx/conf.d:Z \
    -v /root/nginx/logs:/var/log/nginx:Z \
    nginx
