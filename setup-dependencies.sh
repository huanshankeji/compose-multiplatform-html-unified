#!/bin/bash

# Script to set up snapshot dependencies for the project
set -e

echo "Setting up snapshot dependencies..."

# Create temporary directory for dependencies
TEMP_DIR=$(mktemp -d)
cd "$TEMP_DIR"

# Clone and publish gradle-common
echo "Cloning gradle-common..."
git clone https://github.com/huanshankeji/gradle-common.git
cd gradle-common
git checkout 479a4e3af4bc185c65512dd0fb1af35f0f2705d9

echo "Publishing gradle-common dependencies to Maven local..."
./gradlew :common-gradle-dependencies:publishToMavenLocal
./gradlew :kotlin-common-gradle-plugins:publishToMavenLocal
./gradlew :gradle-plugins:publishToMavenLocal

cd "$TEMP_DIR"

# Clone and publish compose-html-material
echo "Cloning compose-html-material..."
git clone https://github.com/huanshankeji/compose-html-material.git
cd compose-html-material
git checkout 7c66f7a2c6a7d7f453417f09d07992d135fbfa94

echo "Publishing compose-html-material dependencies to Maven local..."
./gradlew publishToMavenLocal

# Clean up
cd /
rm -rf "$TEMP_DIR"

echo "Dependencies setup complete!"