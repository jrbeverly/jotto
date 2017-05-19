#!/bin/sh
set -ex

# Variables
#
# Build variables for directories.
SCRIPT=$(readlink -f "$0")
DIR="$(dirname $SCRIPT)"
ROOT_DIR="$(dirname $DIR)"
SRC_DIR="${ROOT_DIR}/jotto"
DOC_DIR="${ROOT_DIR}/doc"

# Build
#
# Build the latex project
mkdir -p $DOC_DIR
cd $SRC_DIR
mvn javadoc:javadoc