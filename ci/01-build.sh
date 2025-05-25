#!/bin/bash

set -e
echo "🔧 Building project..."
mvn -B -q clean package -DskipTests