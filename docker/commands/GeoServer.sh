# GeoServer
# https://github.com/kartoza/docker-geoserver
# chmod a+w data_dir settings
docker run \
    -p 8600:8080 \
    --name geoserver \
    -e STABLE_EXTENSIONS=vectortiles-plugin,wps-plugin,gdal-plugin,dxf-plugin,gwc-s3-plugin,mbstyle-plugin \
    -e COMMUNITY_EXTENSIONS=s3-geotiff-plugin \
    -e GEOSERVER_ADMIN_USER=admin  \
    -e GEOSERVER_ADMIN_PASSWORD=admin \
    -v /home/geoserver/data_dir:/opt/geoserver/data_dir:Z \
    -v /home/geoserver/settings:/settings:Z \
    -d kartoza/geoserver