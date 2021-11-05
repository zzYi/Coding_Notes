docker run -d \
    --name cloudreve \
    -e PUID=1000 \
    -e PGID=1000 \
    -e TZ="Asia/Shanghai" \
    -p 5212:5212 \
    --restart=unless-stopped \
    -v /home/cloudreve/uploads:/cloudreve/uploads \
    -v /home/cloudreve/config:/cloudreve/config \
    -v /home/cloudreve/db:/cloudreve/db \
    -v /home/cloudreve/avatar:/cloudreve/avatar \
    xavierniu/cloudreve