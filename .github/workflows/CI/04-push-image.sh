#!/bin/bash
set -e
echo "📤 Pushing image to Docker Hub..."
echo "$DOCKER_PASSWORD" | docker login -u "$DOCKER_USERNAME" --password-stdin
docker push $DOCKER_USERNAME/abay-restful-api:latest