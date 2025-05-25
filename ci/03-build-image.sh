#!/bin/bash
set -e
echo "🐳 Building Docker image..."
docker build -t "$DOCKER_USERNAME/abay-restful-api:latest" .