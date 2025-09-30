#!/bin/bash

# Wrapper script to build the project with dependencies
set -e

SCRIPT_DIR="$(cd "$(dirname "${BASH_SOURCE[0]}")" && pwd)"
cd "$SCRIPT_DIR"

# Check if snapshot dependencies are available
if ! ./gradlew help --quiet 2>/dev/null; then
    echo "Snapshot dependencies not found. Setting them up..."
    ./setup-dependencies.sh
fi

# Run the actual build
echo "Running build..."
./gradlew "$@"