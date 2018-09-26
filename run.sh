#!/usr/bin/env bash

# Script to deploy the app
deploy(){
# From the remote host-machine, run the following cmd
    docker pull s34n/leader-data-img
    docker run --name='leader-data' -d -it -p 8003:8003 s34n/leader-data-img && docker logs leader-data -f
}

# Tag-And-Push Script to tag & push the app
tagAndPush(){
    docker tag leader-data-img:latest s34n/leader-data-img:latest
    docker push s34n/leader-data-img:latest
}

# Rebuild-Script to clean & build the app using the Dockerfile script
rebuild(){
    gradle clean
    gradle build
    docker build -f Dockerfile -t leader-data-img . --no-cache
    tagAndPush
}

# Let's get rid of the pre-existing docker images on the machine.
if [[ ! -z "$(docker container ps | grep leader-data)" ]]; then
    echo "Leader-Data-Service Docker Container Found"
    docker stop leader-data && docker rm leader-data && docker rmi leader-data-img
    rebuild
else
    echo "Leader-Data-Service Docker Container NOT Found"
    rebuild
fi

