# MySQL 5.7
# The configuration file is docker/configs/MySQL/my.cnf
docker run  \
    -p 3306:3306  \
    --name mysql  \
    -v /home/mysql/mysql:/etc/mysql:Z  \
    -v /home/mysql/logs:/logs:Z  \
    -v /home/mysql/data:/var/lib/mysql:Z  \
    -v /home/mysql/mysql-files:/var/lib/mysql-files:Z  \
    -e MYSQL_ROOT_PASSWORD=PASSWORD -d mysql:5.7

# MongoDB
docker run \
    --name mongodb \
    -p 27017:27017 \
    -v /root/mongodb:/data/db:Z -d mongo

# Redis
# The configuration file is docker/configs/Redis/redis.conf
docker run \
    --name redis \
    -p 16379:16379 \
    -v /home/redis/data:/data:Z \
    -v /home/redis/conf/redis.conf:/etc/redis/redis.conf:Z \
    -d redis redis-server /etc/redis/redis.conf

# Minio console
docker run \
    --name minio \
    -p 9000:9000 \
    -p 9001:9001 \
    -v /home/minio/data:/data:Z \
    -e "MINIO_ROOT_USER=USER" \
    -e "MINIO_ROOT_PASSWORD=PASSWORD" \
    -d minio/minio server /data --console-address ":9001"