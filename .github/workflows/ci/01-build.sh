#!/bin/bash

set -e
echo "🔧 Building project..."
./mvnw clean package -DskipTests