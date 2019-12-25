#!/bin/bash
scriptdir=$(dirname "$0")
dockerfile="${scriptdir}/Dockerfile"
if [ ! -f "${dockerfile}" ]; then
  echo "Can't find ${dockerfile}, aborting."
  exit 1
fi
mvn -f ${scriptdir}/../ clean package && \
  (docker kill $(docker ps -q) || true) 2>/dev/null && \
  docker container rm /poker-app
  docker container run -i --name poker-app com.aliceplatform/poker:1.0-SNAPSHOT