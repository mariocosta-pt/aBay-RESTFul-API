#!/bin/bash

set -e
echo "🔧 Building project..."
mvn clean package -DskipTests