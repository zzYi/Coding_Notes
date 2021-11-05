cp ./minio.service /etc/systemd/system/
systemctl enable minio.service
systemctl daemon-reload
systemctl start minio
systemctl status minio