#!/bin/sh
set -e

# Variables
#
# Build variables for directories.
SCRIPT=$(readlink -f "$0")
DIR="$(dirname $SCRIPT)"

# Environment
docker run --rm -it \
        -v $(dirname $DIR):/media \
        --workdir /media \
        maven:3-jdk-8-alpine sh