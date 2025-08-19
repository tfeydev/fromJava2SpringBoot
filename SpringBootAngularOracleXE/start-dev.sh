#!/bin/bash
# Make executable: chmod +x start-dev.sh

# Load environment variables from .env-dev
set -a
source .env-dev   # oder ./frontend/.env-dev, je nach Ablage
set +a

# Define project paths
BACKEND_DIR="./backend"
FRONTEND_DIR="./frontend"

# Start backend in background
echo "Starting backend..."
mvn -f "$BACKEND_DIR/pom.xml" spring-boot:run &
BACKEND_PID=$!

# Start frontend in foreground
echo "Starting frontend..."
cd "$FRONTEND_DIR" || exit
ng serve &
FRONTEND_PID=$!

# Cleanup on exit
trap "echo 'Stopping...'; kill $BACKEND_PID $FRONTEND_PID" EXIT

# Wait for processes
wait
