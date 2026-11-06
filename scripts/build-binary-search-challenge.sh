#!/usr/bin/env bash
set -euo pipefail
cd "$(dirname "$0")/.."
mkdir -p build/binary-search-challenge docs/java
javac -d build/binary-search-challenge projects/binary-search-challenge/src/*.java
jar cf docs/java/binary-search-challenge.jar -C build/binary-search-challenge .
