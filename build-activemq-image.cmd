@echo off


set ACTIVEMQ_VERSION=2.19.0
set PROXY=%1

cd .\activemq
docker-compose up
cd _TMP_\artemis\%ACTIVEMQ_VERSION%
docker build -f ./docker/Dockerfile-adoptopenjdk-11 --build-arg http_proxy_arg=%PROXY%  -t artemis-adoptopenjdk-11 .
