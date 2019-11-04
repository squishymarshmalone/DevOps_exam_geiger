#!/bin/bash
echo "$DOCKER_PASSWORD" | docker login -u "$DOCKER_USERNAME" --password-stdin

docker build . --tag geigerapp --build-arg JAR_FILE=target/geiger-0.0.1-SNAPSHOT.jar

docker tag geigerapp squishymarshmellow/geigerapp:latest

docker push squishymarshmellow/geigerapp